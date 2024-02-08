package services.room;

import dao.*;
import model.Room;
import request.RoomRequest;


public class RoomService {
    private RoomDao roomDao;
    private HotelDao hotelDao;


    public RoomService(RoomDao roomDao, HotelDao hotelDao) {
        this.roomDao = roomDao;
        this.hotelDao = hotelDao;
    }
    public void saveRoom(RoomRequest roomRequest) {
        Room room = new Room();
        upsertRoom(room, roomRequest);

        roomDao.save(room);
    }

    public void updateRoom(RoomRequest roomRequest, Long roomId) {
        Room room = roomDao.getById(roomId);
        upsertRoom(room, roomRequest);

        roomDao.update(room);
    }

    public void deleteRoom(Long roomId) {
        Room room = roomDao.getById(roomId);
        roomDao.delete(room);
    }

    private void upsertRoom(Room room, RoomRequest roomRequest) {
        room.setType(roomRequest.getType());
        room.setNumber(roomRequest.getNumber());
        room.setPersonCount(roomRequest.getPersonCount());
        room.setPrice(roomRequest.getPrice());
        room.setBathroom(roomRequest.isBathroom());
        room.setHotel(hotelDao.getById(roomRequest.getHotelId()));
    }
}
