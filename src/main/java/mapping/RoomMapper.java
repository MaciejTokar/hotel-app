package mapping;

import model.Room;
import response.RoomResponse;

public class RoomMapper {

    public RoomResponse fromRoomToRoomResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .type(room.getType())
                .number(room.getNumber())
                .personCount(room.getPersonCount())
                .price(room.getPrice())
                .bathroom(room.getBathroom())
                .hotelId(room.getHotel().getId())
                .build();
    }
}
