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

        clientDao.save(client);
    }

    public void updateClient(Long id, ClientRequest clientRequest) {
        Client client = clientDao.getById(id);
        upsertRoom(client, clientRequest);

        clientDao.update(client);
    }

    public void deleteClient(Long clientId) {
        Client client = clientDao.getById(clientId);
        clientDao.delete(client);
    }

    private void upsertRoom(Client client, ClientRequest clientRequest) {
        client.setName(clientRequest.getName());
        client.setSurname(clientRequest.getSurname());
        client.setPesel(clientRequest.getPesel());
        client.setMail(clientRequest.getMail());
        client.setPhone(clientRequest.getPhone());
        client.setCardType(clientRequest.getCardType());
    }
}
