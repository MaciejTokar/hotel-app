package dao;

import model.Reservation;

public class ReservationDao extends CommonDao<Reservation> {

    public ReservationDao() {
        super(Reservation.class);
    }
}
