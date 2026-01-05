package dao;

import entities.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Implémentation de ClientDAO avec Hibernate
 * Gère l'accès aux données dans la base MySQL
 */
public class ImpClientDAO implements ClientDAO {
    
    private SessionFactory sessionFactory;
    
    // Constructeur - Initialise Hibernate
    public ImpClientDAO() {
        try {
            // Création de la SessionFactory à partir de hibernate.cfg.xml
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Client.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de SessionFactory : " + e);
            e.printStackTrace();
        }
    }
    
    /**
     * Ajouter un nouveau client dans la base de données
     */
    @Override
    public void addClient(Client client) {
        Transaction transaction = null;
        Session session = null;
        
        try {
            // Ouvrir une session
            session = sessionFactory.openSession();
            
            // Démarrer une transaction
            transaction = session.beginTransaction();
            
            // Sauvegarder le client
            session.save(client);
            
            // Valider la transaction
            transaction.commit();
            
            System.out.println("✅ Client ajouté : " + client);
            
        } catch (Exception e) {
            // En cas d'erreur, annuler la transaction
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("❌ Erreur lors de l'ajout du client : " + e);
            e.printStackTrace();
        } finally {
            // Fermer la session
            if (session != null) {
                session.close();
            }
        }
    }
    
    /**
     * Supprimer un client par son code
     */
    @Override
    public void deleteClient(Long code) {
        Transaction transaction = null;
        Session session = null;
        
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            // Récupérer le client
            Client client = session.get(Client.class, code);
            
            if (client != null) {
                // Supprimer le client
                session.delete(client);
                System.out.println("✅ Client supprimé : " + client);
            } else {
                System.out.println("⚠️ Client avec code " + code + " introuvable");
            }
            
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("❌ Erreur lors de la suppression : " + e);
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    /**
     * Récupérer un client par son code
     */
    @Override
    public Client getClientByCode(Long code) {
        Session session = null;
        Client client = null;
        
        try {
            session = sessionFactory.openSession();
            
            // Récupérer le client
            client = session.get(Client.class, code);
            
            if (client != null) {
                System.out.println("✅ Client trouvé : " + client);
            } else {
                System.out.println("⚠️ Client avec code " + code + " introuvable");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la récupération : " + e);
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return client;
    }
    
    /**
     * Récupérer tous les clients
     */
    @Override
    public List<Client> getAllClients() {
        Session session = null;
        List<Client> clients = null;
        
        try {
            session = sessionFactory.openSession();
            
            // HQL (Hibernate Query Language)
            Query<Client> query = session.createQuery("FROM Client", Client.class);
            clients = query.list();
            
            System.out.println("✅ Nombre de clients récupérés : " + clients.size());
            
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la récupération de tous les clients : " + e);
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return clients;
    }
    
    // Méthode pour fermer la SessionFactory (à appeler à la fin)
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}