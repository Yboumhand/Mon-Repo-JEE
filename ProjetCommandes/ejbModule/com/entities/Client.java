package com.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;
    
    @Column(name = "nom", length = 100)
    private String nom;
    
    @Column(name = "prenom", length = 100)
    private String prenom;
    
    @Column(name = "ville", length = 100)
    private String ville;
    
    // Relation One-to-One avec Compte_Société
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private CompteSociete compteSociete;
    
    // Relation Many-to-Many avec Produit (Commander)
    @ManyToMany
    @JoinTable(
        name = "client_produit",
        joinColumns = @JoinColumn(name = "client_code"),
        inverseJoinColumns = @JoinColumn(name = "produit_numero")
    )
    private List<Produit> produits = new ArrayList<>();
    
    // Constructeurs
    public Client() {
    }
    
    public Client(String nom, String prenom, String ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
    }
    
    // Getters et Setters
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
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
    
    public String getVille() {
        return ville;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public CompteSociete getCompteSociete() {
        return compteSociete;
    }
    
    public void setCompteSociete(CompteSociete compteSociete) {
        this.compteSociete = compteSociete;
    }
    
    public List<Produit> getProduits() {
        return produits;
    }
    
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
    
    // Méthodes utilitaires
    public void ajouterProduit(Produit produit) {
        this.produits.add(produit);
        produit.getClients().add(this);
    }
    
    public void retirerProduit(Produit produit) {
        this.produits.remove(produit);
        produit.getClients().remove(this);
    }
}