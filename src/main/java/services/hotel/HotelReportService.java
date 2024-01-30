package services.hotel;

import dao.HotelDao;
import mapping.HotelMapper;
import response.HotelResponse;

import java.util.List;
import java.util.stream.Collectors;

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
                .map(hotelMapper::fromHotelToHotelResponse)
                .toList();
    }

    public List<HotelResponse> searchHotelByAddress(String address) {
        return hotelDao.findAll().stream()
                .filter(n -> n.getAddress().equalsIgnoreCase(address))
                .map(hotelMapper::fromHotelToHotelResponse)
                .toList();
    }
    public List<HotelResponse> roomWithSpecificAmenity(List<String> facilityNames) {
        return hotelDao.roomWithSpecificAmenity(facilityNames).stream()
                .map(o -> hotelMapper.fromHotelToHotelResponse(o))
                .collect(Collectors.toList());
    }
}
