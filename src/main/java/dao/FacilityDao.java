package dao;

import config.HibernateUtil;
import exeption.BaseException;
import exeption.ErrorCode;
import model.Facility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FacilityDao extends CommonDao<Facility> {
    public FacilityDao() {
        super(Facility.class);
    }

    @Override
    public void update(Facility facility) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(facility);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new BaseException(ErrorCode.FACILITY_UPDATE_EXCEPTION, String.valueOf(facility.getId()));
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
        }
    }
}
