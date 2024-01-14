package mapping;

import model.Reservation;
import response.ReservationResponse;

public class ReservationMapper {

    public ReservationResponse fromReservationToReservationResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .fromDate(reservation.getFromDate())
                .toDate(reservation.getToDate())
                .roomId(reservation.getRoom().getId())
                .number(reservation.getRoom().getNumber())
                .clientId(reservation.getClient().getId())
                .nameHotel(reservation.getRoom().getHotel().getName())
                .build();
    }
}
