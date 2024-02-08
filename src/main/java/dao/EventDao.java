package dao;

import model.Event;

public class EventDao extends CommonDao<Event> {

    public EventDao() {
        super(Event.class);
    }
}
