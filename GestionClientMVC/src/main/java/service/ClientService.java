package service;

import entities.Client;
import java.util.List;

/**
 * Interface Service pour la logique métier
 * Couche intermédiaire entre le Controller et le DAO
 */
public interface ClientService {
    
    void addClient(Client client);
    void deleteClient(Long code);
    Client getClientByCode(Long code);
    List<Client> getAllClients();
}

