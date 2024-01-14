package request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {
    private String number;
    private String type;
    private Integer personCount;
    private BigDecimal price;
    private boolean bathroom;
    private Long hotelId;
}
