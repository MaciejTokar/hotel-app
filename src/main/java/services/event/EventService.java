package services.event;

import dao.EventDao;
import dao.HotelDao;
import model.Event;
import request.EventRequest;

public class EventService {

    private EventDao eventDao;
    private HotelDao hotelDao;


    public EventService(EventDao eventDao, HotelDao hotelDao) {
        this.eventDao = eventDao;
        this.hotelDao = hotelDao;
    }

    public void saveEvent(EventRequest eventRequest) {
        Event event = new Event();
        upsertEvent(event, eventRequest);

        eventDao.save(event);
    }

    public void updateEvent(Long id, EventRequest eventRequest) {
        Event event = eventDao.getById(id);
        upsertEvent(event, eventRequest);

        eventDao.update(event);
    }

    public void deleteEvent(Long id) {
        Event event = eventDao.getById(id);

        eventDao.delete(event);
    }

    private void upsertEvent(Event event, EventRequest eventRequest) {
        event.setDate(eventRequest.getDate());
        event.setPersonCount(eventRequest.getPersonCount());
        event.setHotel(hotelDao.getById(eventRequest.getHotelId()));
        event.setEvents(eventRequest.getEventType());
    }
}
