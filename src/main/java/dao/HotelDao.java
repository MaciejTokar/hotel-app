package dao;

import exeption.ErrorCode;
import exeption.HotelException;
import exeption.ReviewException;
import model.Hotel;
import java.util.List;
import java.util.Optional;

public class HotelDao extends CommonDao<Hotel> {

    public HotelDao() {
        super(Hotel.class);
    }

    public List<Hotel> roomWithSpecificAmenity(List<String> facilityNames) {
        return executeInSession(session -> {
            List<Hotel> list;

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
    @Override
    public Hotel getById(Long id) {
        return Optional.ofNullable(id)
                .filter(_id -> _id != null)
                .map(e -> executeInSession(session -> session.get(Hotel.class, id)))
                .orElseThrow(() -> new HotelException(ErrorCode.HOTEL_ID_EXCEPTION, String.valueOf(id)));
    }
}
