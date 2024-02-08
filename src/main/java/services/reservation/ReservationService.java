package services.reservation;


import dao.ClientDao;
import dao.ReservationDao;
import dao.RoomDao;
import model.Reservation;
import model.Room;
import request.ReservationRequest;
import request.ReservationUpdateRequest;

public class ReservationService {

    private ReservationDao reservationDao;
    private RoomDao roomDao;
    private ClientDao clientDao;

    public ReservationService(ReservationDao reservationDao, RoomDao roomDao, ClientDao clientDao) {
        this.reservationDao = reservationDao;
        this.roomDao = roomDao;
        this.clientDao = clientDao;
    }

    public void saveReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();

        reservation.setFromDate(reservationRequest.getFromDate());
        reservation.setToDate(reservationRequest.getToDate());
        reservation.setClient(clientDao.getById(reservationRequest.getClientId()));

        for (Long roomId : reservationRequest.getRoomsId()) {
            Room room = roomDao.getById(roomId);
            reservation.setRoom(room);
            reservationDao.save(reservation);
        }
    }

    public void updateReservation(Long id, ReservationUpdateRequest reservationUpdateRequest) {
        Reservation reservation = reservationDao.getById(id);

        reservation.setFromDate(reservationUpdateRequest.getFromDate());
        reservation.setToDate(reservationUpdateRequest.getToDate());
        reservation.setClient(clientDao.getById(reservationUpdateRequest.getClientId()));
        reservation.setRoom(roomDao.getById(reservationUpdateRequest.getRoomId()));

        reservationDao.update(reservation);
    }

    public void deleteReservation(Long reservationId) {
        Reservation reservation = reservationDao.getById(reservationId);
        reservationDao.delete(reservation);
    }

    private void upsertReservation(Reservation reservation, ReservationRequest reservationRequest) {
        reservation.setFromDate(reservationRequest.getFromDate());
        reservation.setToDate(reservationRequest.getToDate());
        reservation.setClient(clientDao.getById(reservationRequest.getClientId()));
    }
}
