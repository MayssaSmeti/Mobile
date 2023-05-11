/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Constat;
import com.mycompany.services.ServiceConstat;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ListConstatForm extends BaseForm{
    Form current;
    public ListConstatForm(Resources res) {
        super("Newsfeed",BoxLayout.y()); 
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List Constat");
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand(e ->  {
            
        });
       
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        add(LayeredLayout.encloseIn(sl ));
           
           
        Label constat = new Label("List Constat");
        constat.setAlignment(Component.CENTER);
        Font boldFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        constat.getUnselectedStyle().setFont(boldFont);

        add(constat);
        
      
        //Appel affichage methode
        ArrayList<Constat> listc = ServiceConstat.getInstance().affichageConstats();
      
        
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        
        for (Constat c : listc) {
           
            TextArea textArea = new TextArea();
            textArea.setText(
                     "Nom_client: " + c.getNomclient_e() + "\n" +
                     "Prénom_client: " + c.getPrenomclient_e() + "\n" +
                     "Type_Véhicule: " + c.getTypevehicule_e() + "\n" +
                     "Marque_Véhicule: " + c.getMarquevehicule_e() + "\n" +
                     "Assurance_client:" +c.getAssuranceclient_e()+ "\n" +
                     "Adresse_client:" +c.getAdresseclient_e()+ "\n" +
                     "Emplacement_Accident:"+c.getEmplacementaccid()+"\n"+
                     "Photo_Accident:"+c.getEmplacementaccid()+"\n"+
                     "Description_Degat:"+c.getDescriptiondegat()+"\n"+
                     "Observations:"+c.getObservations()+"\n"+
                     "Numéro_Contrat:"+c.getNumcontrat_e()+"\n"+
                     "Mail:"+c.getMail()+"\n"+
                     "Date_Création:"+c.getDate_creation()+"\n");
                      textArea.setEditable(false);
                      Container constatContainer = new Container(BoxLayout.y());
                      constatContainer.addComponent(textArea);
        
     Button btnAjouter = new Button("Ajouter");
     btnAjouter.addActionListener((e) -> {
         AjoutConstatForm ajout = new AjoutConstatForm(res);
         ajout.show();
     });
     Button btnModifier = new Button("Modifier");
     btnModifier.addActionListener((e) -> {
         ModifierConstatForm modif = new ModifierConstatForm(res,c);
         modif.show();
     });
     Button btnSupprimer = new Button("Supprimer");
     btnSupprimer.addActionListener((e) -> {
        // afficher un message de confirmation à l'utilisateur
        if (Dialog.show("Confirmer la suppression", "Êtes-vous sûr de vouloir supprimer ce constat ?", "Annuler", "OK")) {
            // l'utilisateur a confirmé la suppression
            boolean deleted = ServiceConstat.getInstance().deleteConstat(c.getId());
        if (deleted) {
            // Devis is deleted successfully, remove the Devis from the UI
            constatContainer.remove();
            Dialog.show("Succès", "Le constat a été supprimé avec succès.", "OK", null);

        } else {
            // Error deleting Devis, show an error message to the user
            Dialog.show("Error", "Failed to delete Constat", "OK", null);
        }
        }  
                });

      Container buttonsContainer = new Container(new GridLayout(1, 3));
      buttonsContainer.addComponent(btnAjouter);
      buttonsContainer.addComponent(btnModifier);
      buttonsContainer.addComponent(btnSupprimer);
      constatContainer.addComponent(buttonsContainer);

        list.add(constatContainer);
        }
        add(list);
    }
}
 
 
  
        
    
        
        
        
        
        
    


   
   
