package services.client;

import dao.ClientDao;
import mapping.ClientMapper;
import response.ClientResponse;

import java.util.*;

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
                .map(clientMapper::fromClientToClientResponse)
                .toList();
    }

    public List<ClientResponse> sortedList() {
        return clientDao.findAll().stream()
                .map(clientMapper::fromClientToClientResponse)
                .sorted(Comparator.comparing(ClientResponse::getName).thenComparing(ClientResponse::getSurname))
                .toList();
    }
}
