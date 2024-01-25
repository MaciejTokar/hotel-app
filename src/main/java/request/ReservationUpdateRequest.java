package request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationUpdateRequest {
        private LocalDate fromDate;
        private LocalDate toDate;
        private Long roomId;
        private Long clientId;
}
