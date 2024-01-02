package services.client;

import dao.ClientDao;
import mapping.ClientMapper;
import response.ClientResponse;

import java.util.List;

public class ClientReportService {

    private ClientDao clientDao;
    private ClientMapper clientMapper;

    public ClientReportService(ClientDao clientDao, ClientMapper clientMapper) {

        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
    }

    public List<ClientResponse> searchClientByName(String name) {
        return clientDao.findAll().stream()
                .filter(n -> n.getName().equals(name))
                .map(clientMapper::byName)
                .toList();
    }

    public List<ClientResponse> searchClientBySurname(String surname) {
        return clientDao.findAll().stream()
                .filter(n -> n.getSurname().equals(surname))
                .map(clientMapper::bySurname)
                .toList();
    }
}
