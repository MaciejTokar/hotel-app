package services.hotel;

import dao.HotelDao;
import model.Hotel;
import request.HotelRequest;

public class HotelService {
    private HotelDao hotelDao;

    public HotelService(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public void saveHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelRequest.getName());
        hotel.setAddress(hotelRequest.getAddress());
        hotelDao.saveHotel(hotel);
    }

    public void updateHotel(Long id, HotelRequest hotelRequest) {
        Hotel hotel = hotelDao.getHotel(id);
        hotel.setName(hotelRequest.getName());
        hotel.setAddress(hotelRequest.getAddress());
        hotelDao.updateHotel(hotel);
    }

    public void deleteHotel(Long hotelId) {
        Hotel hotel = hotelDao.getHotel(hotelId);
        hotelDao.deleteHotel(hotel);
    }
}
