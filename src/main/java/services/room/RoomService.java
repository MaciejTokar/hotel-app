package services.room;

import dao.ClientDao;
import dao.HotelDao;
import dao.RoomDao;
import model.Room;
import request.RoomRequest;


public class RoomService {
    private RoomDao roomDao;
    private HotelDao hotelDao;
    private ClientDao clientDao;

    public RoomService(RoomDao roomDao, HotelDao hotelDao, ClientDao clientDao) {
        this.roomDao = roomDao;
        this.hotelDao = hotelDao;
        this.clientDao = clientDao;
    }
    public void saveRoom(RoomRequest roomRequest) {
        Room room = new Room();
        upsertRoom(room, roomRequest);

        roomDao.saveRoom(room);
    }

    public void updateRoom(RoomRequest roomRequest, Long roomId) {
        Room room = roomDao.getRoom(roomId);
        upsertRoom(room, roomRequest);

        roomDao.updateRoom(room);
    }

    public void deleteRoom(Long roomId) {
        Room room = roomDao.getRoom(roomId);
        roomDao.deleteRoom(room);
    }

    private void upsertRoom(Room room, RoomRequest roomRequest) {
        room.setType(roomRequest.getType());
        room.setNumber(roomRequest.getNumber());
        room.setPersonCount(roomRequest.getPersonCount());
        room.setPrice(roomRequest.getPrice());
        room.setBathroom(roomRequest.isBathroom());
        room.setHotel(hotelDao.getHotel(roomRequest.getHotelId()));
    }
}
