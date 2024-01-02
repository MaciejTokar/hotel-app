package services.room;

import dao.RoomDao;
import mapping.RoomMapper;
import response.RoomResponse;

import java.util.List;

public class RoomReportService {

    private RoomDao roomDao;
    private RoomMapper roomMapper;

    public RoomReportService(RoomDao roomDao, RoomMapper roomMapper) {
        this.roomDao = roomDao;
        this.roomMapper = roomMapper;
    }

    public List<RoomResponse> searchRoom(Integer personCount) {
        return roomDao.findAll().stream()
                .filter(i -> i.getPersonCount().equals(personCount))
                .map(roomMapper::byType)
                .toList();
    }

    public List<RoomResponse> searchRoom(String type) {
        return roomDao.findAll().stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .map(i -> roomMapper.byType(i))
                .toList();
    }
}
