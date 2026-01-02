package com.gestion.model;

public class Sportif {
    private String code;
    private String nom;
    private String prenom;
    private int age;
    private String sexe;
    private String pays;
    private String discipline;
    
    // Constructeur par défaut (obligatoire pour Hibernate)
    public Sportif() {} 
    
    // Constructeur avec paramètres
    public Sportif(String code, String nom, String prenom, int age, String sexe, String pays, String disciplines) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.pays = pays;
        this.discipline = discipline;
    }
    
    // Getters et Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
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
    
    public String getSexe() {
        return sexe;
    }
    
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    public String getPays() {
        return pays;
    }
    
    public void setPays(String pays) {
        this.pays = pays;
    }
    
    public String getDisciplines() {
        return discipline;
    }
    
    public void setDisciplines(String discipline) {
        this.discipline = discipline;
    }
}