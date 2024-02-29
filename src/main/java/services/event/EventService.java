package services.event;

import dao.EventDao;
import dao.HotelDao;
import mapping.EventMapper;
import model.Event;
import request.EventRequest;
import response.EventResponse;

public class EventService {

    private EventDao eventDao;
    private HotelDao hotelDao;
    private EventMapper eventMapper;


    public EventService(EventDao eventDao, HotelDao hotelDao, EventMapper eventMapper) {
        this.eventDao = eventDao;
        this.hotelDao = hotelDao;
        this.eventMapper = eventMapper;
    }

    public EventResponse saveEvent(EventRequest eventRequest) {
        Event event = new Event();
        event = upsertEvent(event, eventRequest);

        eventDao.save(event);

        return eventMapper.fromEventToEventResponse(eventDao.getById(event.getId()));
    }

    public EventResponse updateEvent(Long id, EventRequest eventRequest) {
        Event event = eventDao.getById(id);
        event = upsertEvent(event, eventRequest);

        eventDao.update(event);

        return eventMapper.fromEventToEventResponse(eventDao.getById(event.getId()));
    }

    public void deleteEvent(Long id) {
        Event event = eventDao.getById(id);
        eventDao.delete(event);
    }

    private Event upsertEvent(Event event, EventRequest eventRequest) {
        return event.builder()
                .id(event.getId())
                .date(eventRequest.getDate())
                .personCount(eventRequest.getPersonCount())
                .hotel(hotelDao.getById(eventRequest.getHotelId()))
                .events(eventRequest.getEventType())
                .build();
    }
}
