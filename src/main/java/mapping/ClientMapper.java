package mapping;

import model.Client;
import response.ClientResponse;

public class ClientMapper {

    public ClientResponse byName(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .pesel(client.getPesel())
                .build();
    }

    public ClientResponse bySurname(Client client) {
        return ClientResponse.builder()
                .name(client.getName())
                .surname(client.getSurname())
                .build();
    }
}
