package dao;

import model.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RoomDao extends CommonDao<Room> {

    public RoomDao() {
        super(Room.class);
    }

    @Override
    public void save(Room room) {
        executeInTransaction(session -> {
            session.save(room);
            if (room.getHotel().getId() == null) {
                throw new NullPointerException("Null");
            }
        });
    }

    public List<Room> roomFilter(String name, LocalDate from, LocalDate to, Boolean bathroom, String type, Integer personCount, BigDecimal priceFrom, BigDecimal priceTo) {
        return executeInSession(session -> {
            String hql = "SELECT r FROM Room r " +
                    "JOIN FETCH r.hotel h " +
                    "LEFT JOIN FETCH r.reservations res " +
                    "WHERE h.name = :name " +
                    "AND r.bathroom = :bathroom " +
                    "AND r.personCount = :personCount " +
                    "AND r.type = :type " +
                    "AND r.price BETWEEN :priceFrom AND :priceTo " +
                    "AND (res IS NULL OR (res.fromDate > :to OR res.toDate < :from))";

            return session.createQuery(hql, Room.class)
                    .setParameter("name", name)
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("bathroom", bathroom)
                    .setParameter("type", type)
                    .setParameter("personCount", personCount)
                    .setParameter("priceFrom", priceFrom)
                    .setParameter("priceTo", priceTo)
                    .getResultList();
        });
    }

    public List<Room> roomAmenity() {
        return executeInSession(session -> {
            String hql = "SELECT r FROM Room r " +
                    "JOIN FETCH r.facilities f";

            return session.createQuery(hql, Room.class).list();
        });
    }
}

