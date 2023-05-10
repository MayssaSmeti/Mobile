/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author USER
 */
public class Constat {
    
    private int id;
    private String nomclient_e,prenomclient_e,typevehicule_e,marquevehicule_e,assuranceclient_e,adresseclient_e,emplacementaccid,photoaccid,descriptiondegat,observations,numcontrat_e,mail,Date_creation;
    
    public Constat( String nomclient_e, String prenomclient_e, String typevehicule_e, String marquevehicule_e, String assuranceclient_e, String adresseclient_e, String emplacementaccid, String photoaccid, String descriptiondegat, String observations, String numcontrat_e, String mail, String Date_creation)
    {   
        this.nomclient_e = nomclient_e;
        this.prenomclient_e = prenomclient_e;
        this.typevehicule_e = typevehicule_e;
        this.marquevehicule_e = marquevehicule_e;
        this.assuranceclient_e = assuranceclient_e;
        this.adresseclient_e = adresseclient_e;
        this.emplacementaccid = emplacementaccid;
        this.photoaccid = photoaccid;
        this.descriptiondegat = descriptiondegat;
        this.observations = observations;
        this.numcontrat_e = numcontrat_e;
        this.mail = mail;
        this.Date_creation = Date_creation;    
    }
    
    public Constat(int id,String nomclient_e, String prenomclient_e, String typevehicule_e, String marquevehicule_e, String assuranceclient_e, String adresseclient_e, String emplacementaccid, String photoaccid, String descriptiondegat, String observations, String numcontrat_e, String mail,String Date_creation) {
        this.id = id;
        this.nomclient_e = nomclient_e;
        this.prenomclient_e = prenomclient_e;
        this.typevehicule_e = typevehicule_e;
        this.marquevehicule_e = marquevehicule_e;
        this.assuranceclient_e = assuranceclient_e;
        this.adresseclient_e = adresseclient_e;
        this.emplacementaccid = emplacementaccid;
        this.photoaccid = photoaccid;
        this.descriptiondegat = descriptiondegat;
        this.observations = observations;
        this.numcontrat_e = numcontrat_e;
        this.mail = mail;
        this.Date_creation = Date_creation;
    }

    public Constat() {
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomclient_e() {
        return nomclient_e;
    }

    public void setNomclient_e(String nomclient_e) {
        this.nomclient_e = nomclient_e;
    }

    public String getPrenomclient_e() {
        return prenomclient_e;
    }

    public void setPrenomclient_e(String prenomclient_e) {
        this.prenomclient_e = prenomclient_e;
    }

    public String getTypevehicule_e() {
        return typevehicule_e;
    }

    public void setTypevehicule_e(String typevehicule_e) {
        this.typevehicule_e = typevehicule_e;
    }

    public String getMarquevehicule_e() {
        return marquevehicule_e;
    }

    public void setMarquevehicule_e(String marquevehicule_e) {
        this.marquevehicule_e = marquevehicule_e;
    }

    public String getAssuranceclient_e() {
        return assuranceclient_e;
    }

    public void setAssuranceclient_e(String assuranceclient_e) {
        this.assuranceclient_e = assuranceclient_e;
    }

    public String getAdresseclient_e() {
        return adresseclient_e;
    }

    public void setAdresseclient_e(String adresseclient_e) {
        this.adresseclient_e = adresseclient_e;
    }

    public String getEmplacementaccid() {
        return emplacementaccid;
    }

    public void setEmplacementaccid(String emplacementaccid) {
        this.emplacementaccid = emplacementaccid;
    }

    public String getPhotoaccid() {
        return photoaccid;
    }

    public void setPhotoaccid(String photoaccid) {
        this.photoaccid = photoaccid;
    }

    public String getDescriptiondegat() {
        return descriptiondegat;
    }

    public void setDescriptiondegat(String descriptiondegat) {
        this.descriptiondegat = descriptiondegat;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getNumcontrat_e() {
        return numcontrat_e;
    }

    public void setNumcontrat_e(String numcontrat_e) {
        this.numcontrat_e = numcontrat_e;
    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate_creation() {
        return Date_creation;
    }

    public void setDate_creation(String Date_creation) {
        this.Date_creation = Date_creation;
    }
    

    
}
