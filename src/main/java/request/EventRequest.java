package request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.EventType;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequest {
    private LocalDate date;
    private Integer personCount;
    private Long hotelId;
    private Set<EventType> eventType;
}
