/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author msi
 */
public class ReclamationForm extends Form {
     public ReclamationForm() {
        
        setTitle("CodingSquad | Reclamations");
        Container reclamationContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button ajouterBtn=new Button("Ajouter Reclamation");
        Button afficherBtn=new Button("Afficher Reclamation");
    
        Form addForm = new Form("Ajouter",BoxLayout.y());
        Form viewForm = new Form("Afficher",BoxLayout.y());
    
        /*AJOUT*/
        addForm.setTitle("Ajouter Reclamation");
        Container addContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button ListBtn=new Button("Consulter Liste");
        Button AjouterBtn=new Button("Ajouter");
        
        TextField description=new TextField("","Description");
        TextField objet=new TextField("","Objet");
       
        TextField note=new TextField("","Note");
    
        
        
        addContainer.add(description);
        addContainer.add(objet);
       
        addContainer.add(note);
        
        btnContainer.add(AjouterBtn);
        btnContainer.add(ListBtn);
        
        AjouterBtn.addActionListener((e) -> {
            try {
                if( description.getText().equals("") || objet.getText().equals("") ||  note.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    
                    
                    
                  Reclamation rec = new Reclamation(
                            30,
                           String.valueOf(description.getText()),
                            String.valueOf(objet.getText()),
                           
                            Integer.parseInt(String.valueOf(note.getText()))         
                    );
                    
                    System.out.println("data Reclamation == "+rec);
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceReclamation.getInstance().AjouterReclamation(rec);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    refreshTheme();//Actualisation
                    Dialog.show("CodingSquad","Ajout avec Succés","Annuler","OK");
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
        });
        ListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                viewForm.show();
            }
        });
        addForm.getToolbar().addCommandToLeftBar("Back",null,(ActionListener) (ActionEvent evt1) -> {
                new ReclamationForm().showBack();
        });
        
        addForm.add(addContainer);
        addForm.add(btnContainer);
        
        /*FIN AJOUT*/
        /*VIEW*/
        viewForm.setTitle("Liste des Reclamations");
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Label ListId = new Label("Id: ");
        Label ListDescription = new Label("Description : ");
        Label ListObjet = new Label("Objet : ");
      
        Label ListNote = new Label("Note : ");
        
        viewContainer.add(ListId);
        viewContainer.add(ListDescription);
       
        viewContainer.add(ListObjet);
        
        viewContainer.add(ListNote);
        
        viewForm.add(viewContainer);
        viewForm.getToolbar().addCommandToLeftBar("Back",null,(ActionListener) (ActionEvent evt1) -> {
                new ListeReclamationForm();
        });
        
        /*FIN VIEW*/
        
        
        ajouterBtn.addActionListener(e-> addForm.show());
        afficherBtn.addActionListener(e-> viewForm.show());
        
        reclamationContainer.add(ajouterBtn);
        reclamationContainer.add(afficherBtn);
    
        add(reclamationContainer);
        
        
        
        
        
        getToolbar()
                .addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();
            }
        } );
      
    
        getToolbar()
                .addMaterialCommandToSideMenu("Reclamation",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ReclamationForm().show();
            }
        } );
    
        getToolbar()
                .addMaterialCommandToSideMenu("Reponse",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ReponseForm().show();
            }
        } );
        
        
    }
    
}
