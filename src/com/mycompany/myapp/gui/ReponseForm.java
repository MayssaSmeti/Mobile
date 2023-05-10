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
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.services.ServiceReponse;


/**
 *
 * @author msi
 */
public class ReponseForm extends Form {
      public ReponseForm() {
        
        setTitle("CodingSquad | Reponses");
        Container reponseContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button ajouterBtn=new Button("Ajouter Reponse");
        Button afficherBtn=new Button("Afficher Reponse");
    
        Form addForm = new Form("Ajouter",BoxLayout.y());
        Form viewForm = new Form("Afficher",BoxLayout.y());
    
        /*AJOUT*/
        addForm.setTitle("Ajouter Reponse");
        Container addContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button ListBtn=new Button("Consulter Liste");
        Button AjouterBtn=new Button("Ajouter");
        
        TextField description=new TextField("","Description");
      
        
        
        addContainer.add(description);
     
        
        btnContainer.add(AjouterBtn);
        btnContainer.add(ListBtn);
        
        AjouterBtn.addActionListener((e) -> {
            try {
                if(description.getText().equals("") ) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                  
                    
                    Reponse rep = new Reponse(
                            30,
                            String.valueOf(description.getText())
                          
                                  );
                    
                    System.out.println("data Reponse == "+rep);
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceReponse.getInstance().AjouterReponse(rep);
                    
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
                new ReponseForm().showBack();
        });
        
        addForm.add(addContainer);
        addForm.add(btnContainer);
        
        /*FIN AJOUT*/
        /*VIEW*/
        viewForm.setTitle("Liste des Reponse");
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Label ListDescription = new Label("Description : ");
      
        
                
        viewContainer.add(ListDescription);
     
        
        viewForm.add(viewContainer);
        viewForm.getToolbar().addCommandToLeftBar("Back",null,(ActionListener) (ActionEvent evt1) -> {
                new ReponseForm().showBack();
        });
        
        /*FIN VIEW*/
        
        
        ajouterBtn.addActionListener(e-> addForm.show());
        afficherBtn.addActionListener(e-> viewForm.show());
        
        reponseContainer.add(ajouterBtn);
        reponseContainer.add(afficherBtn);
    
        add(reponseContainer);
        
        
        
        
        
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
