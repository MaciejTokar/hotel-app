package mapping;

import model.Client;
import response.ClientResponse;

public class ClientMapper {

    public ClientResponse fromClientToClientResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .pesel(client.getPesel())
                .build();
    }
}
