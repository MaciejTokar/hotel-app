package dao;

import config.HibernateUtil;
import model.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EventDao {

    public void saveEvent(Event event) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        if (event.getHotel().getId() == null) {
            throw new NullPointerException();
        }
    }

    public void updateEvent(Event event) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteEvent(Event event) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Event getEvent(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Event.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Event> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Event> fromEvent = session.createQuery("from Event", Event.class);
            return fromEvent.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
