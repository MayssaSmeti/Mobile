/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Vehicule;
import com.mycompany.services.ServiceVehicule;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ListVéhiculeForm extends BaseForm {
    Form current;
    public ListVéhiculeForm(Resources res) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List Véhicule");
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
           
           
        Label vehicule = new Label("List Véhicule");
        vehicule.setAlignment(Component.CENTER);
        Font boldFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        vehicule.getUnselectedStyle().setFont(boldFont);

        add(vehicule);
        
      
        //Appel affichage methode
        ArrayList<Vehicule> listv = ServiceVehicule.getInstance().affichageVehicules();
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (Vehicule v : listv) {
           
            TextArea textArea = new TextArea();
            textArea.setText("Id: " + v.getId() + "\n" +
                     "Matricule : " + v.getMatricule() + "\n" +
                     "Type_Véhicule: " + v.getType() + "\n" +
                     "Marque_Véhicule: " + v.getMarque() + "\n" +
                     "Nombre de cheveaux: " + v.getNb_ch() + "\n" 
                     );
                      textArea.setEditable(false);
                      Container vehiculeContainer = new Container(BoxLayout.y());
                      vehiculeContainer.addComponent(textArea);
        
     Button btnAjouter = new Button("Ajouter");
     btnAjouter.addActionListener((e) -> {
         AjoutVehiculeForm ajout = new AjoutVehiculeForm(res);
         ajout.show();
     });
     Button btnModifier = new Button("Modifier");
     btnModifier.addActionListener((e) -> {
         ModifierVehiculeForm modif = new ModifierVehiculeForm(res,v);
         modif.show();
     });
     Button btnSupprimer = new Button("Supprimer");
     btnSupprimer.addActionListener((e) -> {
        // afficher un message de confirmation à l'utilisateur
        if (Dialog.show("Confirmer la suppression", "Êtes-vous sûr de vouloir supprimer ce vehicule ?", "Annuler", "OK")) {
            // l'utilisateur a confirmé la suppression
            boolean deleted = ServiceVehicule.getInstance().deleteVehicule(v.getId());
        if (deleted) {
            vehiculeContainer.remove();
            Dialog.show("Succès", "Le véhicule a été supprimé avec succès.", "OK", null);
        } else {
            Dialog.show("Error", "Failed to delete Vehicule", "OK", null);
        }
        }  
                });

      Container buttonsContainer = new Container(new GridLayout(1, 3));
      buttonsContainer.addComponent(btnAjouter);
      buttonsContainer.addComponent(btnModifier);
      buttonsContainer.addComponent(btnSupprimer);
      vehiculeContainer.addComponent(buttonsContainer);

        list.add(vehiculeContainer);
        }
        add(list);
    }
    
}
