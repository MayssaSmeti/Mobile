/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Lenovo
 */
//taw n7oto fi description
public class Utilisateur {
    
    private int id;
    private String email;
   // private String image;
    private String nom;
    private String prenom;
    private String password;
    private int telephone ; 
    private int cin ;
    private String adresse ; 

    public Utilisateur( String nom, String prenom,String email, String password, int telephone, int cin, String adresse) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.telephone = telephone;
        this.cin = cin;
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    
   

    public Utilisateur(String email, String image, String nom, String prenom, String password) {
        this.email = email;
      //  this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
    }
    
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(String email) {
        this.email = email;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public void setimage(String image) {
//        this.image = image;
//    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", adresse=" + email + '}';
    }

    public Utilisateur(int id, String email, String image, String nom, String prenom) {
        this.id = id;
        this.email = email;
      //  this.image = image;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur(String email, String image, String nom, String prenom) {
        this.email = email;
     //   this.image = image;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    
    
    
    
}