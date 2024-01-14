package services.reservation;

import dao.ReservationDao;
import mapping.ReservationMapper;
import model.Reservation;
import response.ReservationResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
                .map(reservationMapper::fromReservationToReservationResponse)
                .toList();
    }

    public List<ReservationResponse> sortedList() {
        return reservationDao.findAll().stream()
                .map(reservationMapper::fromReservationToReservationResponse)
                .sorted(Comparator.comparing(ReservationResponse::getFromDate).thenComparing(ReservationResponse::getRoomId))
                .toList();
    }

    public Map<Object, BigDecimal> clientPayments() {
        return reservationDao.findAll().stream()
                .collect(Collectors.groupingBy(Reservation::getClient, Collectors.mapping(r -> r.getRoom().getPrice(), Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
       }

    public Map<Long, List<ReservationResponse>> clientReservations() {
        return reservationDao.findAll().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        reservationMapper::fromReservationToReservationResponse
                ))
                .entrySet().stream()
                .collect(Collectors.groupingBy(
                        entry -> entry.getKey().getClient().getId(),
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }

       public BigDecimal earnedByCompany() {
           return reservationDao.findAll().stream()
                   .map(o -> o.getRoom().getPrice())
                   .reduce(BigDecimal.ZERO, BigDecimal::add);
       }

       public Map<String, BigDecimal> earnedByHotel() {
           return reservationDao.findAll().stream()
                   .collect(Collectors.groupingBy(o -> o.getRoom().getHotel().getName(), Collectors.mapping(o -> o.getRoom().getPrice(), Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
       }

    public Map<String, BigDecimal> earnedByHotelMonthly(Integer month) {
        return reservationDao.findAll().stream()
                .filter(r -> Month.of(month).equals(r.getFromDate().getMonth()))
                .collect(Collectors.groupingBy(o -> o.getRoom().getHotel().getName(), Collectors.mapping(o -> o.getRoom().getPrice(), Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
    }
}
