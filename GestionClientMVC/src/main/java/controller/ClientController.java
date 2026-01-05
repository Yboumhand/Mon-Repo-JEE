package controller;

import entities.Client;
import service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Contrôleur Spring MVC pour la gestion des clients
 * Pattern : Controller (MVC)
 * 
 * Ce contrôleur gère toutes les requêtes HTTP liées aux clients
 */
@Controller
public class ClientController {
    
    // Injection de dépendance automatique par Spring
    private ClientService clientService;
    
    ClientController (ClientService clientService){
    	this.clientService=clientService;
    }
    
    /**
     * Page d'accueil - Affiche le formulaire et la liste des clients
     * URL: /index ou /
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        try {
            // Récupérer tous les clients
            List<Client> clients = clientService.getAllClients();
            
            // Ajouter la liste au modèle (accessible dans la JSP)
            model.addAttribute("clients", clients);
            model.addAttribute("message", "Bienvenue dans la gestion des clients");
            
            System.out.println("📋 Affichage de " + clients.size() + " clients");
            
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération des clients : " + e.getMessage());
            e.printStackTrace();
        }
        
        // Retourne le nom de la vue (index.jsp)
        return "index";
    }
    
    /**
     * Ajouter un nouveau client
     * URL: /addClient
     * Méthode: POST
     */
    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public String addClient(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("age") int age,
            @RequestParam("ville") String ville,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Créer un nouveau client
            Client client = new Client(nom, prenom, age, ville);
            
            // Ajouter via le service
            clientService.addClient(client);
            
            // Message de succès
            redirectAttributes.addFlashAttribute("success", 
                "✅ Client " + nom + " " + prenom + " ajouté avec succès !");
            
            System.out.println("✅ Nouveau client ajouté : " + client);
            
        } catch (Exception e) {
            // Message d'erreur
            redirectAttributes.addFlashAttribute("error", 
                "❌ Erreur lors de l'ajout : " + e.getMessage());
            e.printStackTrace();
        }
        
        // Redirection vers la page d'accueil
        return "redirect:/index";
    }
    
    /**
     * Supprimer un client
     * URL: /deleteClient?code=123
     * Méthode: GET
     */
    @RequestMapping(value = "/deleteClient", method = RequestMethod.GET)
    public String deleteClient(
            @RequestParam("code") Long code,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Récupérer le client avant suppression (pour le message)
            Client client = clientService.getClientByCode(code);
            
            if (client != null) {
                // Supprimer le client
                clientService.deleteClient(code);
                
                redirectAttributes.addFlashAttribute("success", 
                    "✅ Client " + client.getNom() + " " + client.getPrenom() + " supprimé avec succès !");
                
                System.out.println("🗑️ Client supprimé : " + client);
            } else {
                redirectAttributes.addFlashAttribute("error", 
                    "⚠️ Client introuvable avec le code : " + code);
            }
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "❌ Erreur lors de la suppression : " + e.getMessage());
            e.printStackTrace();
        }
        
        return "redirect:/index";
    }
    
    /**
     * Rechercher un client par code
     * URL: /searchClient?code=123
     * Méthode: GET
     */
    @RequestMapping(value = "/searchClient", method = RequestMethod.GET)
    public String searchClient(
            @RequestParam("code") Long code,
            Model model) {
        
        try {
            // Rechercher le client
            Client client = clientService.getClientByCode(code);
            
            if (client != null) {
                // Ajouter le client au modèle
                model.addAttribute("searchedClient", client);
                model.addAttribute("success", "✅ Client trouvé !");
            } else {
                model.addAttribute("error", "⚠️ Aucun client trouvé avec le code : " + code);
            }
            
            // Afficher aussi tous les clients
            List<Client> clients = clientService.getAllClients();
            model.addAttribute("clients", clients);
            
        } catch (Exception e) {
            model.addAttribute("error", "❌ Erreur lors de la recherche : " + e.getMessage());
            e.printStackTrace();
        }
        
        return "index";
    }
    
    /**
     * Afficher tous les clients (même fonctionnalité que index)
     * URL: /listClients
     */
    @RequestMapping(value = "/listClients", method = RequestMethod.GET)
    public String listClients(Model model) {
        return index(model);
    }
}