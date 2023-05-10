/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import java.util.List;
import com.codename1.io.JSONParser;
import com.codename1.ui.Display;
import com.codename1.ui.list.DefaultListModel;
import java.util.ArrayList;



public class Devis {

    private int id;


    private int idExpert;

    
    private Date date;

    
    private float totalHt;

    
    private List<DevisItem> items;

    public Devis() {
        // Default constructor
    }

    public Devis(int id, String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public int getIdExpert() {
        return idExpert;
    }

    public void setIdExpert(int idExpert) {
        this.idExpert = idExpert;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotalHt() {
        return totalHt;
    }

    public void setTotalHt(float totalHt) {
        this.totalHt = totalHt;
    }

    public List<DevisItem> getItems() {
        return items;
    }

    public void setItems(List<DevisItem> items) {
        this.items = items;
    }

    public void addItem(DevisItem item) {
        items.add(item);
        item.setDevis(this);
        calculateTotalHt();
    }

    public void removeItem(DevisItem item) {
        items.remove(item);
        item.setDevis(null);
        calculateTotalHt();
    }

    private void calculateTotalHt() {
        totalHt = 0;
        for (DevisItem item : items) {
            totalHt += item.getTotalprice();
        }
    }

    public void setDevisItems(ArrayList<DevisItem> devisItems) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     public List<DevisItem> getDevisItems() {
        return items;
    }

  

    
}
