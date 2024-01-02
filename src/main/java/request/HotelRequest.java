package request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRequest {
//    @NotBlank
    private String name;
//    @NotBlank
    private String address;
}
