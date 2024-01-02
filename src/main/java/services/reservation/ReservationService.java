package services.reservation;


import dao.ClientDao;
import dao.ReservationDao;
import dao.RoomDao;
import model.Reservation;
import request.ReservationRequest;

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
        upsertReservation(reservation, reservationRequest);

        reservationDao.saveReservation(reservation);
    }

    public void updateReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        upsertReservation(reservation, reservationRequest);

        reservationDao.updateReservation(reservation);
    }

    public void deleteReservation(Long reservationId) {
        Reservation reservation = reservationDao.getReservation(reservationId);
        reservationDao.deleteReservation(reservation);
    }

    private void upsertReservation(Reservation reservation, ReservationRequest reservationRequest) {
        reservation.setFromDate(reservationRequest.getFromDate());
        reservation.setToDate(reservationRequest.getToDate());
        reservation.setClient(clientDao.getClient(reservationRequest.getClientId()));
        reservation.setRoom(roomDao.getRoom(reservationRequest.getRoomId()));
    }
}
