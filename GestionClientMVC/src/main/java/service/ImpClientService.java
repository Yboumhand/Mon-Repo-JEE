package service;


import dao.ClientDAO;
import entities.Client;
import java.util.List;

/**
 * Implémentation du service Client
 * Utilise le DAO pour manipuler les données
 */
public class ImpClientService implements ClientService {
    
    // Injection de dépendance - sera injectée par Spring
    private ClientDAO clientDAO;
    
    // Setter pour l'injection de dépendance (utilisé par Spring)
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
    
    /**
     * Méthode d'initialisation (appelée après la création du bean)
     * Définie dans spring-beans.xml avec init-method="initialiser"
     */
    public void initialiser() {
        System.out.println("🚀 Service Client initialisé avec succès !");
        // Vous pouvez ajouter du code d'initialisation ici si nécessaire
    }
    
    @Override
    public void addClient(Client client) {
        // Validation métier (optionnel)
        if (client == null) {
            throw new IllegalArgumentException("Le client ne peut pas être null");
        }
        
        if (client.getNom() == null || client.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du client est obligatoire");
        }
        
        if (client.getPrenom() == null || client.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom du client est obligatoire");
        }
        
        if (client.getAge() <= 0) {
            throw new IllegalArgumentException("L'âge doit être positif");
        }
        
        // Appeler le DAO pour ajouter le client
        clientDAO.addClient(client);
    }
    
    @Override
    public void deleteClient(Long code) {
        if (code == null || code <= 0) {
            throw new IllegalArgumentException("Code invalide");
        }
        
        clientDAO.deleteClient(code);
    }
    
    @Override
    public Client getClientByCode(Long code) {
        if (code == null || code <= 0) {
            throw new IllegalArgumentException("Code invalide");
        }
        
        return clientDAO.getClientByCode(code);
    }
    
    @Override
    public List<Client> getAllClients() {
        List<Client> clients = clientDAO.getAllClients();
        
        // Vous pouvez ajouter de la logique métier ici
        // Par exemple : trier, filtrer, etc.
        
        return clients;
    }
}