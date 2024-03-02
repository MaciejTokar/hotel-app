package dao;

import exeption.ErrorCode;
import exeption.EventException;
import model.Event;

import java.util.Optional;

public class EventDao extends CommonDao<Event> {
    public EventDao() {
        super(Event.class);
    }

    @Override
    public Event getById(Long id) {
        return Optional.ofNullable(id)
                .filter(_id -> _id != null)
                .map(e -> executeInSession(session -> session.get(Event.class, id)))
                .orElseThrow(() -> new EventException(ErrorCode.EVENT_ID_EXCEPTION, String.valueOf(id)));
    }
}
