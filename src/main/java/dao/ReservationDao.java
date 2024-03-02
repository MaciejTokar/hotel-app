package dao;

import exeption.ErrorCode;
import exeption.ReservationException;
import model.Reservation;

import java.util.Optional;

public class ReservationDao extends CommonDao<Reservation> {

    public ReservationDao() {
        super(Reservation.class);
    }

    @Override
    public Reservation getById(Long id) {
        return Optional.ofNullable(id)
                .filter(_id -> _id != null)
                .map(e -> executeInSession(session -> session.get(Reservation.class, id)))
                .orElseThrow(() -> new ReservationException(ErrorCode.RESERVATION_ID_EXCEPTION, String.valueOf(id)));
    }
}
