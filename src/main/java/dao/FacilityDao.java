package dao;

import config.HibernateUtil;
import model.Facility;
import model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FacilityDao {

    public void saveFacility(Facility facility) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(facility);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void addFacilityToRoom(List<Long> roomIds, Facility facility) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            for (Long roomId : roomIds) {
                Room room = session.get(Room.class, roomId);
                room.getFacilities().add(facility);
                facility.getRooms().add(room);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateFacility(Facility facility) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(facility);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteFacility(Facility facility) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(facility);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteFacilityOfRoom(Long facilityId, Long roomId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery("DELETE FROM room_facility WHERE facility_id = :facilityId AND room_id = :roomId")
                    .setParameter("facilityId", facilityId)
                    .setParameter("roomId", roomId)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Facility getFacility(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Facility.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Facility> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Facility> fromFacility = session.createQuery("from Facility", Facility.class);
            return fromFacility.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
