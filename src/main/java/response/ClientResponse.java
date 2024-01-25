package response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.CardType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponse {
    private Long id;
    private String name;
    private String surname;
    private String pesel;
    private CardType cardType;
}
