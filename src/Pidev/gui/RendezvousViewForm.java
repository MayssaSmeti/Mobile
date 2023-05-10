/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pidev.gui;

import Pidev.entities.RendezVous;
import Pidev.services.ServiceRendezvous;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author rayen
 */
public class RendezvousViewForm extends Form {
    
    Form current;
    public RendezvousViewForm(){
        /*VIEW*/
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Afficher Offres");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        ArrayList<RendezVous>ra = ServiceRendezvous.getInstance().affichageRendezvous();
        
        
        for(RendezVous rdv : ra ) {
            
            Label lieu = new Label("Titre: "+rdv.getLieu());
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + lieu);
            Label date = new Label("date : "+rdv.getDate());
            Label separator = new Label("************************");
            
            Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            cn.add(lieu);
            cn.add(date);
            
            
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
                        new RendezvousViewForm().show();
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
        
        
        

        
        
        
       getToolbar().addCommandToLeftBar("Back", null, (ActionListener) (ActionEvent evt1) -> {
  //  new RendezvousForm().showBack();
});

        
        
       
    
}

     public RendezvousViewForm(Resources theme) {
        // Constructor with Resources argument
        this();
        // Use the "theme" object as needed
    
        /*FIN VIEW*/
    }
}