package services.reservation;


import dao.ClientDao;
import dao.ReservationDao;
import dao.RoomDao;
import mapping.ReservationMapper;
import model.Reservation;
import model.Room;
import request.ReservationRequest;
import request.ReservationUpdateRequest;
import response.ReservationResponse;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReservationService {

    private ReservationDao reservationDao;
    private RoomDao roomDao;
    private ClientDao clientDao;
    private ReservationMapper reservationMapper;

    public ReservationService(ReservationDao reservationDao, RoomDao roomDao, ClientDao clientDao, ReservationMapper reservationMapper) {
        this.reservationDao = reservationDao;
        this.roomDao = roomDao;
        this.clientDao = clientDao;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationResponse> saveReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        List<Reservation> listResponse = new ArrayList<>();

        reservation.setFromDate(reservationRequest.getFromDate());
        reservation.setToDate(reservationRequest.getToDate());
        reservation.setClient(clientDao.getById(reservationRequest.getClientId()));

        for (Long roomId : reservationRequest.getRoomsId()) {
            Room room = roomDao.getById(roomId);
            reservation.setRoom(room);
            reservationDao.save(reservation);
            listResponse.add(reservation);
        }

        return listResponse.stream()
                .map(r -> reservationMapper.fromReservationToReservationResponse(r))
                .collect(Collectors.toList());
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
}
