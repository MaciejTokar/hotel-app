package mapping;

import model.Room;
import response.RoomResponse;

public class RoomMapper {

    public RoomResponse byType(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .type(room.getType())
                .number(room.getNumber())
                .personCount(room.getPersonCount())
                .price(room.getPrice())
                .hotelId(room.getHotel().getId())
                .build();
    }
}
