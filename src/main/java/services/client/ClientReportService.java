package services.client;

import dao.ClientDao;
import mapping.ClientMapper;
import model.Client;
import response.ClientResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
                .filter(client -> Objects.nonNull(client.getName()) && client.getName().equals(name))
                .map(clientMapper::fromClientToClientResponse)
                .toList();
    }
    public List<ClientResponse> sortedList() {
        return clientDao.findAll().stream()
                .map(clientMapper::fromClientToClientResponse)
                .sorted(Comparator.comparing(ClientResponse::getName, Comparator.nullsLast(String::compareTo))
                        .thenComparing(ClientResponse::getSurname, Comparator.nullsLast(String::compareTo)))
                .toList();
    }

    public BigDecimal calculatePriceForReservation(Long clientId, Long hotelId, LocalDate from, LocalDate to) {
        Client client = clientDao.getById(clientId);

        BigDecimal costRooms = client.getReservations().stream()
                .filter(o -> o.getRoom().getHotel().getId().equals(hotelId))
                .map(o -> o.getRoom().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long days = ChronoUnit.DAYS.between(from, to);

        BigDecimal price = costRooms.multiply(BigDecimal.valueOf(days));

        return client.getCardType() == null
                ? price
                : price.subtract(price.multiply(BigDecimal.valueOf(client.getCardType().getDiscount()).divide(BigDecimal.valueOf(100))));
    }
}
