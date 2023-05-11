/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

//import static com.codename1.push.PushContent.setTitle;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
//import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
//import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
//import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.services.ServiceOffre;
import java.util.ArrayList;

/**
 *
 * @author zaghd
 */
public class OffreViewForm extends Form{
    
    Form current;
    public OffreViewForm(){
        /*VIEW*/
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Afficher Offres");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        ArrayList<Offre>offs = ServiceOffre.getInstance().AfficherOffre();
        
        
        for(Offre off : offs ) {
            
            Label titre = new Label("Titre: "+off.getTitre());
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + titre);
            Label description = new Label("Description : "+off.getDescription());
            Label prix = new Label("Prix : "+off.getPrix());
            Label validite_offre = new Label("Validite Offre: "+off.getValidite_offre());
            Label image_offre = new Label("Image : "+off.getImage_offre());
            Label separator = new Label("**************************");
            
            Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            cn.add(titre);
            cn.add(description);
            cn.add(prix);
            cn.add(validite_offre);
            cn.add(image_offre);
            
            
            Label ActSupprimer = new Label(" ");
            ActSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(ActSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            ActSupprimer.setIcon(suprrimerImage);
            ActSupprimer.setTextPosition(RIGHT);

            //click delete icon
            ActSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if(dig.show("Suppression","Vous voulez supprimer cet Offre?","Annuler","Oui")) {
                    dig.dispose();
                }
                else {
                    dig.dispose();
                     }
                    //n3ayto l suuprimer men service 
                   // if(ServiceOffre.getInstance().deleteOffre(off.getId())) {
                        new OffreViewForm().show();
                   // }

            });
            
            //Update icon 
            Label ActModifier = new Label(" ");
            ActModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(ActModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);

            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            ActModifier.setIcon(mFontImage);
            ActModifier.setTextPosition(LEFT);


            /*ActModifier.addPointerPressedListener(l -> {
                new ActiviteEditForm(act).show();
            });*/


            cn.add(ActSupprimer);
            cn.add(ActModifier);
            cn.add(separator);

            add(cn);
        }
        
        add(viewContainer);
        
        
        

        
        
        
        getToolbar().addCommandToLeftBar("Back",null,(ActionListener) (ActionEvent evt1) -> {
                new OffreForm().showBack();
        });
        
        /*FIN VIEW*/
    }
}
