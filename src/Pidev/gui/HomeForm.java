/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pidev.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.util.Resources;
/**
 *
 * @author rayen
 */
public class HomeForm extends Form{
    
    
    public HomeForm(){
        
    
        Container homeContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Button rendezvous = new Button("Rendezvous");
        
        setLayout(BoxLayout.y());
        setTitle("4Roues| Assurances ");
        
        Label welcome = new Label("Bienvenue Chez 4RouesAssurances");
        
        
      //  rendezvous.addActionListener(e-> new RendezvousForm().show());
        ;
        
        
        homeContainer.add(welcome);
        
        btnContainer.add(rendezvous);
        
        
        
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
                .addMaterialCommandToSideMenu("Offres",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
        //        new RendezvousForm().show();
            }
        } );
    
        
    }
    
     public HomeForm(Resources theme) {
        // Constructor with Resources argument
        this();
        // Use the "theme" object as needed
    }
}

