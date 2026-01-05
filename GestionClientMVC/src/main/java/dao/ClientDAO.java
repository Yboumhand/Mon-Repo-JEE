package dao;

import entities.Client;
import java.util.List;

/**
 * Interface DAO pour la gestion des clients
 * Définit les opérations CRUD
 */
public interface ClientDAO {
    
    /**
     * Ajouter un nouveau client
     * @param client Le client à ajouter
     */
    void addClient(Client client);
    
    /**
     * Supprimer un client par son code
     * @param code Le code du client à supprimer
     */
    void deleteClient(Long code);
    
    /**
     * Récupérer un client par son code
     * @param code Le code du client
     * @return Le client trouvé ou null
     */
    Client getClientByCode(Long code);
    
    /**
     * Récupérer tous les clients
     * @return Liste de tous les clients
     */
    List<Client> getAllClients();
}
