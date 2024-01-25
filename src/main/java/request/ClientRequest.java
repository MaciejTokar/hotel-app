package request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.CardType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest {
    private String name;
    private String surname;
    private String pesel;
    private String mail;
    private String phone;
    private CardType cardType;
}
