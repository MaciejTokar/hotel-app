package dao;

import exeption.ClientException;
import exeption.ErrorCode;
import exeption.HotelException;
import model.Client;
import model.Hotel;

import java.util.Optional;

public class ClientDao extends CommonDao<Client> {

    public ClientDao() {
        super(Client.class);
    }

    @Override
    public Client getById(Long id) {
        return Optional.ofNullable(id)
                .filter(_id -> _id != null)
                .map(e -> executeInSession(session -> session.get(Client.class, id)))
                .orElseThrow(() -> new ClientException(ErrorCode.CLIENT_ID_EXCEPTION, String.valueOf(id)));
    }
}
