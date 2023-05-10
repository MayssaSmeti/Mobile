/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codename1.uikit.cleanmodern;

/**
 *
 * @author USER
 */

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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Constat;
import com.mycompany.services.ServiceConstat;

public class ModifierConstatForm extends BaseForm {
    Form current;

     public ModifierConstatForm(Resources res,Constat c) {
        super("Newsfeed",BoxLayout.y());
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Constat");
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
           
           
        Label con = new Label("Modifier Constat");
        con.setAlignment(Component.CENTER);
        Font boldFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        con.getUnselectedStyle().setFont(boldFont);

        add(con);
            
        TextField nomclient_e = new TextField("","Entrer votre nom");
        nomclient_e.setUIID("TextFieldBlack");
        addStringValue("Nom : ",nomclient_e);  
        
        TextField prenomclient_e = new TextField("","Entrer votre Prenom:");
        prenomclient_e.setUIID("TextFieldBlack");
        addStringValue("Prenom : ",prenomclient_e); 
        
        //TextField typevehicule_e = new TextField("","Choisir votre type de Vehicule");
        ComboBox<String> typevehicule_e = new ComboBox<>("Choisir votre type de Vehicule");
        typevehicule_e.addItem("Voiture");
        typevehicule_e.addItem("Moto");
        typevehicule_e.addItem("Camion");
        typevehicule_e.setUIID("ComboBox");
        addStringValue("Type Vehicule :",typevehicule_e);
               
        //TextField marquevehicule_e = new TextField("", "Choisir votre marque de Vehicule");
        ComboBox<String> marquevehicule_e = new ComboBox<>( "Choisir votre marque de Vehicule");
        marquevehicule_e.addItem("Hyundai");
        marquevehicule_e.addItem("Kia");
        marquevehicule_e.addItem("Mercedes");
        marquevehicule_e.addItem("BMW");
        marquevehicule_e.addItem("Clio");
        marquevehicule_e.addItem("Symbole");   
        marquevehicule_e.setUIID("ComboBox");
        addStringValue("Marque Vehicule :",marquevehicule_e);
        
        
        
        TextField assuranceclient_e = new TextField ("","Entrer votre assurance");
         assuranceclient_e.setUIID("TextFieldBlack");
         addStringValue("Assurance :",assuranceclient_e);

        TextField adresseclient_e = new TextField("","Entrer votre adresse");
        adresseclient_e.setUIID("TextFieldBlack");
        addStringValue("Adresse :",adresseclient_e);

        TextField emplacementaccid = new TextField("","Entrer votre emplacement d'accident:");
        emplacementaccid.setUIID("TextFieldBlack");
        addStringValue("Emplacement Accident :",emplacementaccid);
        
        TextField photoaccid = new TextField("","Ouvrir votre caméra");
        photoaccid.setUIID("TextFieldBlack");
        addStringValue("Photo Accident :", photoaccid);

        TextField observations = new TextField("","Créér votre observations");
        observations.setUIID("TextFieldBlack");
        addStringValue("Observations :",observations);

        TextField descriptiondegat = new TextField("","Créér votre description de degat");
        descriptiondegat.setUIID("TextFieldBlack");
        addStringValue("Description Degat :",descriptiondegat);

        TextField numcontrat_e = new TextField("","Entrer votre numéro de contrat");
       numcontrat_e.setUIID("TextFieldBlack");
        addStringValue(" Numéro Contrat :", numcontrat_e);
        
        
        TextField mail = new TextField("","Entrer votre mail");
        mail.setUIID("TextFieldBlack");
        addStringValue(" Email :", mail);
        
        Picker Date_creation = new Picker();
        Date_creation.setUIID("Picker");
        addStringValue(" Date_Creation :", Date_creation);
               
        
        Button btnModifier = new Button("Modifier");
        addStringValue("", btnModifier);
        
        
        //onclick button event 

        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InfiniteProgress ip = new InfiniteProgress();
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    Constat con = new Constat();
                    con.setId(c.getId());
                    con.setNomclient_e(c.getNomclient_e());
                    con.setPrenomclient_e(c.getPrenomclient_e());
                    con.setTypevehicule_e(c.getTypevehicule_e());
                    con.setMarquevehicule_e(c.getMarquevehicule_e());
                    con.setAssuranceclient_e(c.getAssuranceclient_e());
                    con.setAdresseclient_e(c.getAdresseclient_e());
                    con.setEmplacementaccid(c.getEmplacementaccid());
                    con.setPhotoaccid(c.getPhotoaccid());
                    con.setDescriptiondegat(c.getDescriptiondegat());
                    con.setObservations(c.getObservations());
                    con.setNumcontrat_e(c.getNumcontrat_e());
                    con.setMail(c.getMail());
                    con.setDate_creation(c.getDate_creation());
                    
                    System.out.println("data  constat == "+c);
                    if(ServiceConstat.getInstance().modifierConstat(c)){
                        new ListConstatForm(res).show();
                    }
                    iDialog.dispose();
                    refreshTheme();
                    Dialog.show("Succès", "Le constat a été modifié avec succès.", "OK", null);
                }
                catch(Exception ex ) {
                    ex.printStackTrace();
                }
            }
        });
     }
     
     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
       .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
      }
   
   
}

  
        
