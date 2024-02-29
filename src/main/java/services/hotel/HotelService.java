package services.hotel;

import dao.HotelDao;
import mapping.HotelMapper;
import model.Hotel;
import request.HotelRequest;
import response.HotelResponse;

public class HotelService {
    private HotelDao hotelDao;
    private HotelMapper hotelMapper;

    public HotelService(HotelDao hotelDao, HotelMapper hotelMapper) {
        this.hotelDao = hotelDao;
        this.hotelMapper = hotelMapper;
    }

    public HotelResponse saveHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel();
        hotel = upsertHotel(hotel, hotelRequest);

        hotelDao.save(hotel);

        return hotelMapper.fromHotelToHotelResponse(hotelDao.getById(hotel.getId()));
    }

    public HotelResponse updateHotel(Long id, HotelRequest hotelRequest) {
        Hotel hotel = hotelDao.getById(id);
        hotel = upsertHotel(hotel, hotelRequest);

        hotelDao.update(hotel);

        return hotelMapper.fromHotelToHotelResponse(hotelDao.getById(hotel.getId()));
    }

    public void deleteHotel(Long hotelId) {
        Hotel hotel = hotelDao.getById(hotelId);
        hotelDao.delete(hotel);
    }

    public Hotel upsertHotel(Hotel hotel, HotelRequest hotelRequest) {
        return hotel.builder()
                .id(hotel.getId())
                .name(hotelRequest.getName())
                .address(hotelRequest.getAddress())
                .build();
    }
}
