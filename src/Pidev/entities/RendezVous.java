/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pidev.entities;

import java.util.Date;

/**
 *
 * @author rayen
 */
public class RendezVous {
    int id;
    String Lieu;
    Date date ;

    public RendezVous() {
    }

    public RendezVous(int id) {
        this.id = id;
    }

    public RendezVous(int id, String Lieu, Date date) {
        this.id = id;
        this.Lieu = Lieu;
        this.date = date;
    }

    public RendezVous(String Lieu, Date date) {
        this.Lieu = Lieu;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RendezVous other = (RendezVous) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", Lieu=" + Lieu + ", date=" + date + '}';
    }
    
    
    
    
    
}
