/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Devis;

import Entity.DevisItem;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.uikit.cleanmodern.NewsfeedForm;
import service.ServiceDevis;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author SCORPIO-12
 */
public class AddDevisItemForm extends BaseForm {

    public AddDevisItemForm(Resources res) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Devis");
         getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        
        tb.addSearchCommand(e ->  {   
        });
        
        setTitle("Add Devis Item");
        setLayout(BoxLayout.y());
        TextField description = new TextField("", "Description");
        description.setUIID("TextFieldBlack");
        description.getStyle().setFgColor(154245);
        
        TextField quantite = new TextField("", "Quantite");
        quantite.getStyle().setFgColor(154245);
        
        TextField unitprice = new TextField("", "Unit Price");
        unitprice.getStyle().setFgColor(154245);

        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((description.getText().length() == 0) || (quantite.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        DevisItem DevisItem;
                        DevisItem = new DevisItem(Integer.parseInt(quantite.getText()), Double.parseDouble(unitprice.getText()), description.getText().toString());
                       
                        if (ServiceDevis.getInstance().AddDevisItem(DevisItem)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                        
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }
            }
            });
        
            addAll(description,quantite,unitprice,btnValider);
        
    }
}
