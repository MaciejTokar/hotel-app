package dao;

import model.Client;

public class ClientDao extends CommonDao<Client> {

    public ClientDao() {
        super(Client.class);
    }
}
