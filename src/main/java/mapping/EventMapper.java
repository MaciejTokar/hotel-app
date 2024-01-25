package mapping;

import model.Event;
import response.EventResponse;

public class EventMapper {

    public EventResponse fromEventToEventResponse(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .date(event.getDate())
                .personCount(event.getPersonCount())
                .hotelId(event.getHotel().getId())
                .eventType(event.getEvents())
                .build();
    }
}
