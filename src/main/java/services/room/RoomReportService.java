package services.room;

import dao.HotelDao;
import dao.RoomDao;
import mapping.RoomMapper;
import model.Facility;
import model.Room;
import response.RoomResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomReportService {
    private RoomMapper roomMapper;
    private RoomDao roomDao;
    private HotelDao hotelDao;

    public RoomReportService(RoomDao roomDao, RoomMapper roomMapper) {
        this.roomDao = roomDao;
        this.roomMapper = roomMapper;
    }

    public RoomReportService(RoomDao roomDao, RoomMapper roomMapper, HotelDao hotelDao) {
        this.roomDao = roomDao;
        this.roomMapper = roomMapper;
        this.hotelDao = hotelDao;
    }

    public List<RoomResponse> searchRoom(Integer personCount) {
        return roomDao.findAll().stream()
                .filter(i -> i.getPersonCount().equals(personCount))
                .map(roomMapper::fromRoomToRoomResponse)
                .toList();
    }

    public List<RoomResponse> searchRoom(String type) {
        return roomDao.findAll().stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .map(i -> roomMapper.fromRoomToRoomResponse(i))
                .toList();
    }

    public List<RoomResponse> roomFilter(String name, LocalDate from, LocalDate to, Boolean bathroom, String type, Integer personCount, BigDecimal priceFrom, BigDecimal priceTo) {
        return roomDao.roomFilter(name, from, to, bathroom, type, personCount, priceFrom, priceTo).stream()
                .map(o -> roomMapper.fromRoomToRoomResponse(o))
                .collect(Collectors.toList());
    }

    public Map<Long, List<Long>> roomAmenity() {
        return roomDao.roomAmenity().stream()
                .collect(Collectors.toMap(Room::getId, r -> r.getFacilities().stream().map(Facility::getId).collect(Collectors.toList()), (existing, replacement) -> replacement));
    }
}
