package com.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fournisseurs")
public class Fournisseur implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;
    
    @Column(name = "nom", length = 100)
    private String nom;
    
    @Column(name = "adresse", length = 255)
    private String adresse;
    
    // Relation One-to-Many avec Produit
    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produit> produits = new ArrayList<>();
    
    // Constructeurs
    public Fournisseur() {
    }
    
    public Fournisseur(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
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
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
        produit.setFournisseur(this);
    }
    
    public void retirerProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setFournisseur(null);
    }
}