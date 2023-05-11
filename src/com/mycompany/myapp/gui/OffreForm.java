/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.services.ServiceOffre;

/**
 *
 * @author zaghd
 */
public class OffreForm extends Form{
    
    Form current;
    public OffreForm() {
                
        current=this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Ajouter Un Offre");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        
        
        
        
    
        /*AJOUT*/
        
        Container addContainer = new Container(new FlowLayout(CENTER,CENTER));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button ListBtn=new Button("Consulter Offre");
        Button AjouterBtn=new Button("Ajouter");
        
        TextField offtit=new TextField("","Titre");
        TextField offdes=new TextField("","Description");
        TextField offprix=new TextField("","Prix");
        TextField offval=new TextField("","Validité Offre");
        TextField offimg=new TextField("","Image Offre");
        
        
        addContainer.add(offtit);
        addContainer.add(offdes);
        addContainer.add(offprix);
        addContainer.add(offval);
        addContainer.add(offimg);
        
        
        btnContainer.add(AjouterBtn);
        btnContainer.add(ListBtn);
        btnContainer.setLayout(BoxLayout.y());
        
        AjouterBtn.addActionListener((e) -> {
            try {
                if(offtit.getText().equals("") || offdes.getText().equals("") || offprix.getText().equals("") || offval.getText().equals("") || offimg.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                                        
                    //njibo iduser men session (current user)
                    Offre a = new Offre(
                            30,
                            String.valueOf(offdes.getText()),
                            Integer.parseInt(String.valueOf(offprix.getText())),
                            String.valueOf(offtit.getText()),
                            String.valueOf(offval.getText()),
                            String.valueOf(offimg.getText())
                                  );
                    
                    System.out.println("data offre == "+a);
                    
                    
                    //appelle methode ajouterOffre mt3 service Offre bch nzido données ta3na fi base 
                    ServiceOffre.getInstance().AjouterOffre(a);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    refreshTheme();//Actualisation
                    Dialog.show("4RouesAssurances","Offre ajouté avec Succés","Annuler","OK");
                }
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
        });
        
        ListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new OffreViewForm().show();
            }
        });
        
        add(addContainer);
        add(btnContainer);
        
        
        
        
        
        /*FIN AJOUT*/
        
        
        
        /*NAVBAR*/
        getToolbar()
                .addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();
            }
        } );
        getToolbar()
                .addMaterialCommandToSideMenu("Offre",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new OffreForm().show();
            }
        } );
        
        
    }

    
    
       
}
