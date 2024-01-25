package mapping;

import model.Facility;
import model.Room;
import response.FacilityResponse;

import java.util.List;
import java.util.stream.Collectors;

public class FacilityMapper {

    public FacilityResponse fromFacilityToFacilityResponse(Facility facility) {
                List<Long> roomIds = facility.getRooms().stream()
                .map(Room::getId)
                .collect(Collectors.toList());

        return FacilityResponse.builder()
                .id(facility.getId())
                .description(facility.getDescription())
                .name(facility.getName())
                .roomsId(roomIds)
                .build();
    }
}
