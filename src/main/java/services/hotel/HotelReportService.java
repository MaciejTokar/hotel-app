package services.hotel;

import dao.HotelDao;
import mapping.HotelMapper;
import model.Hotel;
import response.HotelResponse;

import java.util.List;

public class HotelReportService {

    private HotelDao hotelDao;
    private HotelMapper hotelMapper;

    public HotelReportService(HotelDao hotelDao, HotelMapper hotelMapper) {
        this.hotelDao = hotelDao;
        this.hotelMapper = hotelMapper;
    }

    public List<HotelResponse> searchHotelByName(String name) {
        return hotelDao.findAll().stream()
                .filter(n -> n.getName().equalsIgnoreCase(name))
                .map(i -> hotelMapper.byHotelData(i))
                .toList();
    }

    public List<HotelResponse> searchHotelByAddress(String address) {
        return hotelDao.findAll().stream()
                .filter(n -> n.getAddress().equalsIgnoreCase(address))
                .map(i -> hotelMapper.byHotelData(i))
                .toList();
    }


}
