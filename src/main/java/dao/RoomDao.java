package dao;

import config.HibernateUtil;
import model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class RoomDao {

    public void saveRoom(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(room);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        if (room.getHotel().getId() == null) {
            throw new NullPointerException("Null");
        }

    }

    public void updateRoom(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(room);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteRoom(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(room);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Room getRoom(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Room.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Room> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Room> fromRoom = session.createQuery("from Room", Room.class);
            return fromRoom.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Room> roomFilter(String name, LocalDate from, LocalDate to, Boolean bathroom, String type, Integer personCount, BigDecimal priceFrom, BigDecimal priceTo) {
        Transaction transaction = null;
        List<Room> list = Collections.emptyList();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "SELECT r FROM Room r " +
                    "JOIN FETCH r.hotel h " +
                    "LEFT JOIN FETCH r.reservations res " +
                    "WHERE h.name = :name " +
                    "AND r.bathroom = :bathroom " +
                    "AND r.personCount = :personCount " +
                    "AND r.type = :type " +
                    "AND r.price BETWEEN :priceFrom AND :priceTo " +
                    "AND (res IS NULL OR (res.fromDate > :to OR res.toDate < :from))";

            list = session.createQuery(hql, Room.class)
                    .setParameter("name", name)
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .setParameter("bathroom", bathroom)
                    .setParameter("type", type)
                    .setParameter("personCount", personCount)
                    .setParameter("priceFrom", priceFrom)
                    .setParameter("priceTo", priceTo)
                    .getResultList();

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    public List<Room> roomAmenity() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "SELECT r FROM Room r " +
                    "JOIN FETCH r.facilities f";

            transaction.commit();
            return session.createQuery(hql, Room.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
