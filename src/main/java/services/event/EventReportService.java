package services.event;

import dao.EventDao;
import mapping.EventMapper;
import response.EventResponse;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventReportService {

    private EventDao eventDao;
    private EventMapper eventMapper;

    public EventReportService(EventDao eventDao, EventMapper eventMapper) {
        this.eventDao = eventDao;
        this.eventMapper = eventMapper;
    }

    public List<EventResponse> sortedList() {
        return eventDao.findAll().stream()
                .map(o -> eventMapper.fromEventToEventResponse(o))
                .sorted(Comparator.comparing(EventResponse::getHotelId).thenComparing(EventResponse::getDate))
                .collect(Collectors.toList());
    }

    public Map<String, Long> sumEvent() {
        return eventDao.findAll().stream()
                .collect(Collectors.groupingBy(o -> o.getHotel().getName(), Collectors.counting()));
    }
}
