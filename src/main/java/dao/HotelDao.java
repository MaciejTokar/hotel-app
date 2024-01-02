package dao;


import config.HibernateUtil;
import exeption.HotelException;
import model.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HotelDao {
    public void saveHotel(Hotel hotel) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(hotel);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
//            Logger.getLogger(HotelDao.class.getName()).log(Level.SEVERE, null, e);
            throw new HotelException(HotelException.Code.DUPLICATE_EXCEPTION);
        }

    }

    public void updateHotel(Hotel hotel) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(hotel);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HotelException(HotelException.Code.DUPLICATE_EXCEPTION);
        }
    }

    public void deleteHotel(Hotel hotel) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(hotel);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

//    public void deleteHotel(Hotel hotel) {
//        Transaction transaction = null;
//        Session session = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//            session.delete(hotel);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null && transaction.isActive()) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }


    public Hotel getHotel(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Hotel hotel = session.get(Hotel.class, id);
            if (hotel == null) {
                throw new HotelException(HotelException.Code.HOTEL_ID_EXCEPTION);
            }
            return hotel;
        } catch (HotelException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Ala ma kota"); // do poprawy
        }
    }

    public Hotel getHotelByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // przy użyciu FetchType.EAGER
//            String hql = "FROM Hotel WHERE name = :name";
//            przy użyciu FetchType.LAZY
            String hql = "FROM Hotel h LEFT JOIN FETCH h.rooms WHERE h.name = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            return (Hotel)
                    query.uniqueResult();
        } catch (Exception e) {
            Logger.getLogger(HotelDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public List<Hotel> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Hotel> fromHotel = session.createQuery("from Hotel", Hotel.class);
            return fromHotel.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
