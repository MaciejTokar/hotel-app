package services.room;

import dao.*;
import mapping.RoomMapper;
import model.Room;
import request.RoomRequest;
import response.RoomResponse;


public class RoomService {
    private RoomDao roomDao;
    private HotelDao hotelDao;
    private RoomMapper roomMapper;


    public RoomService(RoomDao roomDao, HotelDao hotelDao, RoomMapper roomMapper) {
        this.roomDao = roomDao;
        this.hotelDao = hotelDao;
    }
    public RoomResponse saveRoom(RoomRequest roomRequest) {
        Room room = new Room();
        room = upsertRoom(room, roomRequest);

        roomDao.save(room);

        return roomMapper.fromRoomToRoomResponse(roomDao.getById(room.getId()));
    }

    public RoomResponse updateRoom(Long roomId, RoomRequest roomRequest) {
        Room room = roomDao.getById(roomId);
        room = upsertRoom(room, roomRequest);

        roomDao.update(room);

        return roomMapper.fromRoomToRoomResponse(roomDao.getById(room.getId()));
    }

    public void deleteRoom(Long roomId) {
        Room room = roomDao.getById(roomId);
        roomDao.delete(room);
    }

    private Room upsertRoom(Room room, RoomRequest roomRequest) {
        return room.builder()
                .id(room.getId())
                .type(roomRequest.getType())
                .number(roomRequest.getNumber())
                .personCount(roomRequest.getPersonCount())
                .price(roomRequest.getPrice())
                .bathroom(roomRequest.getBathroom())
                .hotel(hotelDao.getById(roomRequest.getHotelId()))
                .build();
    }
}
