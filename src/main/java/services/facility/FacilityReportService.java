package services.facility;

import dao.FacilityDao;
import dao.HotelDao;
import exeption.BaseException;
import exeption.ErrorCode;
import mapping.FacilityMapper;
import model.Facility;
import model.Hotel;
import model.Room;
import response.FacilityResponse;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FacilityReportService {

    private FacilityDao facilityDao;
    private FacilityMapper facilityMapper;
    private HotelDao hotelDao;

    public FacilityReportService(FacilityDao facilityDao, FacilityMapper facilityMapper, HotelDao hotelDao) {
        this.facilityDao = facilityDao;
        this.facilityMapper = facilityMapper;
        this.hotelDao = hotelDao;
    }

    public FacilityResponse findPopularFacility() {
        List<Hotel> hotels = hotelDao.findAll();

         List<Facility> facilities = hotels.stream()
                .flatMap(hotel -> hotel.getRooms().stream())
                .flatMap(room -> room.getFacilities().stream())
                .collect(Collectors.toList());

        Map<Facility, Long> collect = facilities.stream()
                .collect(Collectors.groupingBy(f -> f, Collectors.counting()));

        return collect.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .map(f -> facilityMapper.fromFacilityToFacilityResponse(f))
                .orElseThrow(() -> new BaseException(ErrorCode.FACILITY_NOT_EXIST));
    }
}
