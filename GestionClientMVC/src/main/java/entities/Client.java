package entities;

import java.io.Serializable;

/**
 * Entité Client - Représente un client dans la base de données
 * Pattern : Model (MVC)
 */
public class Client implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Attributs
    private Long code;           // Clé primaire
    private String nom;          // Nom du client
    private String prenom;       // Prénom du client
    private int age;             // Âge du client
    private String ville;        // Ville du client
    
    // Constructeur par défaut (obligatoire pour Hibernate)
    public Client() {
        super();
    }
    
    // Constructeur avec paramètres
    public Client(Long code, String nom, String prenom, int age, String ville) {
        super();
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.ville = ville;
    }
    
    // Constructeur sans code (pour l'insertion - auto-increment)
    public Client(String nom, String prenom, int age, String ville) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.ville = ville;
    }
    
    // Getters et Setters
    public Long getCode() {
        return code;
    }
    
    public void setCode(Long code) {
        this.code = code;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getVille() {
        return ville;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    // toString() pour l'affichage
    @Override
    public String toString() {
        return "Client [code=" + code + ", nom=" + nom + ", prenom=" + prenom + 
               ", age=" + age + ", ville=" + ville + "]";
    }
}