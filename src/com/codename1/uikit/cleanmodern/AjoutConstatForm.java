/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.ListConstatForm;
import com.mycompany.entities.Constat;

import com.mycompany.services.ServiceConstat;

/**
 *
 * @author USER
 */
public class AjoutConstatForm extends BaseForm{
    
    Form current;
    public AjoutConstatForm(Resources res) {
        super("Newsfeed",BoxLayout.y());
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Constat");
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
           
           
        Label constat = new Label("Ajouter Constat");
        constat.setAlignment(Component.CENTER);
        Font boldFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        constat.getUnselectedStyle().setFont(boldFont);

        add(constat);
            
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

        /*photoaccid.addActionListener(e -> {
            // Ouvrir la caméra pour prendre une photo
            Capture.capturePhoto(photo -> {
                // Afficher la photo prise dans une ImageViewer
                Image image = Image.createImage(photo);
                ImageViewer imageViewer = new ImageViewer(image);
                Dialog.show("Photo", imageViewer);
                // Code pour envoyer la photo à votre serveur
            });
             });*/
         /*Button openCameraButton = new Button("Open Camera");
        FontImage.setMaterialIcon(openCameraButton, FontImage.MATERIAL_CAMERA);*/
 
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
               
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        //onclick button event 
        btnAjouter.addActionListener((e) -> {
            try {
                
                if(nomclient_e.getText().equals("") || prenomclient_e.getText().equals("") || descriptiondegat.getText().equals("") ||  observations.getText().equals("")
                        || numcontrat_e.getText().equals("") || emplacementaccid.getText().equals("") ||  adresseclient_e.getText().equals("") || assuranceclient_e.getText().equals("")
                        || mail.getText().equals("") || Date_creation.getText().equals("") ) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                                        
                    Constat c = new Constat(
                                  String.valueOf(nomclient_e.getText()).toString(),
                                  String.valueOf(prenomclient_e.getText()).toString(),
                                  String.valueOf(typevehicule_e.getSelectedItem()).toString(),
                                  String.valueOf(marquevehicule_e.getSelectedItem()).toString(),
                                  String.valueOf(assuranceclient_e.getText()).toString(),
                                  String.valueOf(adresseclient_e.getText()).toString(),
                                  String.valueOf(emplacementaccid.getText()).toString(),
                                  String.valueOf(photoaccid.getText()).toString(),
                                  String.valueOf(descriptiondegat.getText()).toString(),
                                  String.valueOf(observations.getText()).toString(),
                                  String.valueOf(numcontrat_e.getText()).toString(),
                                  String.valueOf(mail.getText()).toString(),
                                  String.valueOf(Date_creation.getText()).toString());

                    System.out.println("data  constat == "+c);    
                    ServiceConstat.getInstance().ajoutConstat(c);
                    iDialog.dispose();
                    refreshTheme();      
                    Dialog.show("Succès", "Le constat a été ajouté avec succès.", "OK", null);
                }
                
            }
            catch(Exception ex ) {
                ex.printStackTrace();
            }
            
        });
     }
     
     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
       .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
      }
     
}
