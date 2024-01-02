package services.reservation;

import dao.ReservationDao;
import mapping.ReservationMapper;
import model.Reservation;
import response.ReservationResponse;

import java.time.LocalDate;
import java.util.List;

public class ReservationReportService {
    private ReservationDao reservationDao;
    private ReservationMapper reservationMapper;

    public ReservationReportService(ReservationDao reservationDao, ReservationMapper reservationMapper) {
        this.reservationDao = reservationDao;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationResponse> searchReservation(LocalDate from, LocalDate to) {
        return reservationDao.findAll().stream()
                .filter(o -> o.getFromDate().isAfter(from) && o.getToDate().isBefore(to))
                .map(reservationMapper::from)
                .toList();
    }
}
