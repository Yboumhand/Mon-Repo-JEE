package com.session;

import com.entities.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ImpClient implements ILocalClient {
    
    @PersistenceContext(unitName = "ProjetCommandes")
    private EntityManager em;
    
    @Override
    public void addClient(Client client) {
        em.persist(client);
    }
    
    @Override
    public void deleteClient(Client client) {
        Client clientToDelete = em.find(Client.class, client.getCode());
        if (clientToDelete != null) {
            em.remove(clientToDelete);
        }
    }
    
    @Override
    public void updateClient(Client client) {
        em.merge(client);
    }
    
    @Override
    public Client getClient(Integer code) {
        return em.find(Client.class, code);
    }
    
    @Override
    public List<Client> getAllClients() {
        Query query = em.createQuery("SELECT c FROM Client c");
        return query.getResultList();
    }
}