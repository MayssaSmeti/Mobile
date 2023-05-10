/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author SCORPIO-12
 */
public class DevisItem {
    private int id,quantite,devis_id;
    private double unitprice,totalprice;
    private String description;

    public DevisItem(int id, int quantite, int devis_id, double unitprice, double totalprice, String description) {
        this.id = id;
        this.quantite = quantite;
        this.devis_id = devis_id;
        this.unitprice = unitprice;
        this.totalprice = totalprice;
        this.description = description;
    }

    public DevisItem() {
    }

    public DevisItem(int quantite, double unitprice, String description) {
        
        this.quantite = quantite;
        this.unitprice = unitprice;
        this.description = description;
    }

    public DevisItem(int itemId, String description, int quantite, double unitprice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getDevis_id() {
        return devis_id;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setDevis_id(int devis_id) {
        this.devis_id = devis_id;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DevisItem{" + "id=" + id + ", quantite=" + quantite + ", devis_id=" + devis_id + ", unitprice=" + unitprice + ", totalprice=" + totalprice + ", description=" + description + '}';
    }

    void setDevis(Devis aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
