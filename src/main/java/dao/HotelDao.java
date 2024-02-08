package dao;

import config.HibernateUtil;
import model.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class HotelDao extends CommonDao<Hotel> {

    public HotelDao() {
        super(Hotel.class);
    }

    public List<Hotel> roomWithSpecificAmenity(List<String> facilityNames) {
        return executeInSession(session -> {
            List<Hotel> list = Collections.emptyList();

            String hql = "SELECT h FROM Hotel h " +
                    "JOIN FETCH h.rooms r " +
                    "JOIN r.facilities f " +
                    "WHERE f.name IN :facilityNames";

            list = session.createQuery(hql, Hotel.class)
                    .setParameter("facilityNames", facilityNames)
                    .getResultList();

            return list;
        });
    }
}
