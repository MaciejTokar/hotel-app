package mapping;

import model.Hotel;
import response.HotelResponse;

public class HotelMapper {

    public HotelResponse fromHotelToHotelResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .address(hotel.getAddress())
                .build();
    }
}
