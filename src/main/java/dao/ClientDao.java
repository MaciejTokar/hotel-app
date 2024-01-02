package dao;

import config.HibernateUtil;
import exeption.HotelException;
import model.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientDao {

    public void saveClient(Client client) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HotelException(HotelException.Code.DUPLICATE_EXCEPTION);
        }
    }

    public void updateClient(Client client) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HotelException(HotelException.Code.DUPLICATE_EXCEPTION);
        }
    }

    public void deleteClient(Client client) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Client getClient(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Client client = session.get(Client.class, id);
            if (client == null) {
                throw new HotelException(HotelException.Code.CLIENT_ID_EXCEPTION);
            }
            return client;
        } catch (HotelException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Ala ma kota");
        }
    }

    public List<Client> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Client> fromClient = session.createQuery("from Client", Client.class);
            return fromClient.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
