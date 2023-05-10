/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Vehicule;
import com.mycompany.services.ServiceVehicule;

/**
 *
 * @author USER
 */
public class AjoutVehiculeForm extends BaseForm{
    
    Form current;
    public AjoutVehiculeForm(Resources res) {
         super("Newsfeed",BoxLayout.y());
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Vehicule");
        getContentPane().setScrollVisible(false);
                
        super.addSideMenu(res);
        
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
   
                
        Label vehicule = new Label("Ajouter Vehicule");
        vehicule.setAlignment(Component.CENTER);
        Font boldFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        vehicule.getUnselectedStyle().setFont(boldFont);

        add(vehicule);
    
        TextField matricule = new TextField("", "Entrer votre matricule", 20, TextField.NUMERIC);
        matricule.setUIID("TextFieldBlack");
        addStringValue("Matricule", matricule);
        
        ComboBox type = new ComboBox("Sélectionnez Type de votre vehicule");
        type.addItem("Voiture");
        type.addItem("Moto");
        type.addItem("Camion");
        type.setUIID("ComboBox");
        addStringValue("Type Vehicule :",type);
        
        ComboBox marque = new ComboBox("Sélectionnez Marque de votre vehicule");
        marque.addItem("Hyundai");
        marque.addItem("Kia");
        marque.addItem("Mercedes");
        marque.addItem("BMW");
        marque.addItem("Clio");
        marque.addItem("Symbole");
        marque.setUIID("ComboBox");
        addStringValue("Marque Vehicule :",marque);    
        
        
        TextField nb_ch = new TextField("", "Entrer nombre de chevaux", 20, TextField.NUMERIC);
        nb_ch.setUIID("TextFieldBlack");
        addStringValue("Nombre de chevaux :", nb_ch);
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        btnAjouter.addActionListener((e) -> {
            try {
            if(matricule.getText().equals("") || type.getSelectedItem().equals("") || marque.getSelectedItem().equals("") ||  nb_ch.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                                        
                    Vehicule v = new Vehicule(
                                  String.valueOf(matricule.getText()).toString(),
                                  String.valueOf(type.getSelectedItem()).toString(),
                                  String.valueOf(marque.getSelectedItem()).toString(),
                                  String.valueOf(nb_ch.getText()).toString());

                    System.out.println("data  vehicule == "+v);
                    ServiceVehicule.getInstance().ajoutVehicule(v);
                    iDialog.dispose();
                    refreshTheme();
                    Dialog.show("Succès", "Le véhicule a été ajouté avec succès.", "OK", null);
                }
            }
            catch(Exception ex ) {
                ex.printStackTrace();
            }
            
        });
    }
    
      private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
     
}
    

