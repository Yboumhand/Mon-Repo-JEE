package com.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comptes_societe")
public class CompteSociete implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private Integer numero;
    
    @Column(name = "type", length = 50)
    private String type;
    
    // Relation One-to-One avec Client
    @OneToOne
    @JoinColumn(name = "client_code", unique = true, nullable = false)
    private Client client;
    
    // Constructeurs
    public CompteSociete() {
    }
    
    public CompteSociete(String type) {
        this.type = type;
    }
    
    // Getters et Setters
    public Integer getNumero() {
        return numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
}