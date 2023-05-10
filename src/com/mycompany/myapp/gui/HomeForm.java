/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author msi
 */
public class HomeForm extends Form {
    
        public HomeForm(){
        
        
        Container homeContainer = new Container(new FlowLayout(CENTER,CENTER));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        
        Button reclamation = new Button("Reclamation");
        
        Button reponse = new Button("Reponse");
        setLayout(BoxLayout.y());
        setTitle("CodingSquad | AgenceAssurance");
        
        Label welcome = new Label("Welcome To CodingSquad");
        
        
     
        reclamation.addActionListener(e-> new ReclamationForm().show());
     
        reponse.addActionListener(e-> new ReponseForm().show());
        
        
        homeContainer.add(welcome);
        
      
        btnContainer.add(reclamation);
      
        btnContainer.add(reponse);
        
        
        
        add(homeContainer);
        add(btnContainer);
        
        
        
        
        
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
