/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author USER
 */
public class Vehicule {
    
    private int id;
    private String matricule,type,marque,nb_ch;

    public Vehicule() {
    }

    public Vehicule(int id, String matricule, String type, String marque, String nb_ch) {
        this.id = id;
        this.matricule = matricule;
        this.type = type;
        this.marque = marque;
        this.nb_ch = nb_ch;
    }

    public Vehicule(String matricule, String type, String marque, String nb_ch) {
        this.matricule = matricule;
        this.type = type;
        this.marque = marque;
        this.nb_ch = nb_ch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getNb_ch() {
        return nb_ch;
    }

    public void setNb_ch(String nb_ch) {
        this.nb_ch = nb_ch;
    }
    
    
    
    
    
}
