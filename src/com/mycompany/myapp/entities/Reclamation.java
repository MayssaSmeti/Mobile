/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author msi
 */
public class Reclamation {
    
    private int id;
    //private String nomUserReclamation,emailUserReclamation;
    private String objet,description;
    private int note;

    public Reclamation() {
    }

    public Reclamation(int note,String objet, String description) {
        this.objet = objet;
        this.description = description;
        this.note = note;
    }

  

    public Reclamation(int id, String objet, String description, int note) {
        this.id = id;
        this.objet = objet;
        this.description = description;
        this.note = note;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
    
   
}
