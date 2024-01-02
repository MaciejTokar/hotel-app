package response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {
    private Long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Long roomId;
    private String number;
    private Long clientId;
    private String nameHotel;
}
