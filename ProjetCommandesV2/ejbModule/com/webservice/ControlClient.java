package com.webservice;

import com.entities.Client;
import com.session.ILocalClient;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Stateless
@WebService(serviceName = "ControlClientService")
public class ControlClient {
    
    @EJB
    private ILocalClient clientService;
    
    @WebMethod(operationName = "EnregistrerClient")
    public String enregistrerClient(
            @WebParam(name = "nom") String nom,
            @WebParam(name = "prenom") String prenom,
            @WebParam(name = "ville") String ville) {
        
        try {
            Client client = new Client(nom, prenom, ville);
            clientService.addClient(client);
            return "Client enregistré avec succès : " + nom + " " + prenom;
        } catch (Exception e) {
            return "Erreur lors de l'enregistrement : " + e.getMessage();
        }
    }
    
    @WebMethod(operationName = "SupprimerClient")
    public String supprimerClient(@WebParam(name = "code") Integer code) {
        try {
            Client client = clientService.getClient(code);
            if (client != null) {
                clientService.deleteClient(client);
                return "Client supprimé avec succès : " + client.getNom() + " " + client.getPrenom();
            } else {
                return "Client introuvable avec le code : " + code;
            }
        } catch (Exception e) {
            return "Erreur lors de la suppression : " + e.getMessage();
        }
    }
    
    @WebMethod(operationName = "ListerClients")
    public String listerClients() {
        try {
            StringBuilder result = new StringBuilder("Liste des clients:\n");
            for (Client c : clientService.getAllClients()) {
                result.append("- Code: ").append(c.getCode())
                      .append(", Nom: ").append(c.getNom())
                      .append(" ").append(c.getPrenom())
                      .append(", Ville: ").append(c.getVille())
                      .append("\n");
            }
            return result.toString();
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }
}