package com.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produits")
public class Produit implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private Integer numero;
    
    @Column(name = "prix")
    private Float prix;
    
    @Column(name = "marque", length = 100)
    private String marque;
    
    // Relation Many-to-One avec Fournisseur
    @ManyToOne
    @JoinColumn(name = "fournisseur_code", nullable = false)
    private Fournisseur fournisseur;
    
    // Relation Many-to-Many avec Client (côté inverse)
    @ManyToMany(mappedBy = "produits")
    private List<Client> clients = new ArrayList<>();
    
    // Constructeurs
    public Produit() {
    }
    
    public Produit(Float prix, String marque) {
        this.prix = prix;
        this.marque = marque;
    }
    
    // Getters et Setters
    public Integer getNumero() {
        return numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
    public Float getPrix() {
        return prix;
    }
    
    public void setPrix(Float prix) {
        this.prix = prix;
    }
    
    public String getMarque() {
        return marque;
    }
    
    public void setMarque(String marque) {
        this.marque = marque;
    }
    
    public Fournisseur getFournisseur() {
        return fournisseur;
    }
    
    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    
    public List<Client> getClients() {
        return clients;
    }
    
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}