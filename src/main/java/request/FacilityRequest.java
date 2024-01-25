package request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityRequest {
    private Long id;
    private String description;
    private String name;
    private List<Long> roomsId;
}
