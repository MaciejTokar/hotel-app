package dao;

import config.HibernateUtil;
import exeption.HotelException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class CommonDao<T> {
    private final Class<T> entityType;

    public CommonDao(Class<T> entityType) {
        this.entityType = entityType;
    }

    public void save(T entity) {
        executeInTransaction(session -> session.save(entity));
    }

    public void update(T entity) {
        executeInTransaction(session -> session.update(entity));
    }


    public void delete(T entity) {
        executeInTransaction(session -> session.delete(entity));
    }

    public T getById(Long id) {
        return executeInSession(session -> session.get(entityType, id));
    }

    public List<T> findAll() {
        return executeInSession(session -> session.createQuery("from " + entityType.getName(), entityType).list());
    }

    protected void executeInTransaction(Consumer<Session> operation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            operation.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HotelException(HotelException.Code.DUPLICATE_EXCEPTION);
        }
    }

     protected <R> R executeInSession(Function<Session, R> operation) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return operation.apply(session);
        } catch (Exception e) {
            throw new RuntimeException("Błąd operacji na bazie danych", e);
        }
    }
}
