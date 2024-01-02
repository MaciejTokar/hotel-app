package dao;

import config.HibernateUtil;
import exeption.HotelException;
import lombok.EqualsAndHashCode;
import model.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDao {

    public void saveReservation(Reservation reservation) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        if (reservation.getClient().getId() == null) {
            throw new NullPointerException("Client id is null");
        }
        if (reservation.getRoom().getId() == null) {
            throw new NullPointerException("Room id is null");
        }
    }

    public void updateReservation(Reservation reservation) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteReservation(Reservation reservation) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Reservation getReservation(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Reservation.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Reservation> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Reservation> fromReservation = session.createQuery("from Reservation", Reservation.class);
            return fromReservation.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
