package services.client;

import dao.ClientDao;
import mapping.ClientMapper;
import model.Client;
import request.ClientRequest;
import response.ClientResponse;

public class ClientService {

    private ClientDao clientDao;
    private ClientMapper clientMapper;

    public ClientService(ClientDao clientDao, ClientMapper clientMapper) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
    }

    public ClientResponse saveClient(ClientRequest clientRequest) {
        Client client = new Client();
        client = upsertClient(client, clientRequest);

        clientDao.save(client);

        return clientMapper.fromClientToClientResponse(clientDao.getById(client.getId()));
    }

    public ClientResponse updateClient(Long id, ClientRequest clientRequest) {
        Client client = clientDao.getById(id);
        client = upsertClient(client, clientRequest);

        clientDao.update(client);

        return clientMapper.fromClientToClientResponse(clientDao.getById(client.getId()));
    }

    public void deleteClient(Long clientId) {
        Client client = clientDao.getById(clientId);
        clientDao.delete(client);
    }

    private Client upsertClient(Client client, ClientRequest clientRequest) {
        return client.builder()
                .id(client.getId())
                .name(clientRequest.getName())
                .surname(clientRequest.getSurname())
                .pesel(clientRequest.getPesel())
                .mail(clientRequest.getMail())
                .phone(clientRequest.getPhone())
                .cardType(clientRequest.getCardType())
                .build();
    }
}
