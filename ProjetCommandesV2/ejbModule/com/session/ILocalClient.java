package com.session;

import com.entities.Client;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ILocalClient {
    
    void addClient(Client client);
    
    void deleteClient(Client client);

    void updateClient(Client client);

    Client getClient(Integer code);

    List<Client> getAllClients();
}