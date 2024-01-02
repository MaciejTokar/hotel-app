package services.client;

import dao.ClientDao;
import model.Client;
import request.ClientRequest;

public class ClientService {

    private ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void saveClient(ClientRequest clientRequest) {
        Client client = new Client();
        upsertRoom(client, clientRequest);

        clientDao.saveClient(client);
    }

    public void updateClient(ClientRequest clientRequest) {
        Client client = new Client();
        upsertRoom(client, clientRequest);

        clientDao.updateClient(client);
    }

    public void deleteClient(Long clientId) {
        Client client = clientDao.getClient(clientId);
        clientDao.deleteClient(client);
    }

    private void upsertRoom(Client client, ClientRequest clientRequest) {
        client.setName(clientRequest.getName());
        client.setSurname(clientRequest.getSurname());
        client.setPesel(clientRequest.getPesel());
        client.setMail(clientRequest.getMail());
        client.setPhone(clientRequest.getPhone());
    }
}
