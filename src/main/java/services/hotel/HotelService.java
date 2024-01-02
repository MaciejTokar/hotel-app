package services.hotel;

import dao.HotelDao;
import exeption.HotelException;
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


    public void updateHotel(Long hotelId, HotelRequest hotelRequest) {
        Hotel hotel = hotelDao.getHotel(hotelId);
        hotel.setName(hotelRequest.getName());
        hotel.setAddress(hotelRequest.getAddress());
        hotelDao.updateHotel(hotel);
    }

    public void deleteHotel(Long hotelId) {
        Hotel hotel = hotelDao.getHotel(hotelId);
        hotelDao.deleteHotel(hotel);
    }
}
