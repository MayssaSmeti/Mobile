/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Devis;

import Entity.Devis;
import Entity.DevisItem;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import service.ServiceDevis;

/**
 *
 * @author SCORPIO-12
 */
public class EditForm extends BaseForm {
  private Devis devis;
    private ListDevisForm prevForm;
    public EditForm(ListDevisForm prevForm, Resources res, Devis devis) {
        super("Newsfeed", BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Devis");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        setLayout(BoxLayout.y());
        TextField description = new TextField("", "Description");
        description.setUIID("TextFieldBlack");
        description.getStyle().setFgColor(154245);

        TextField quantite = new TextField("", "Quantite");
        quantite.getStyle().setFgColor(154245);

        TextField unitprice = new TextField("", "Unit Price");
        unitprice.getStyle().setFgColor(154245);

         Button saveButton = new Button("Save");
        saveButton.addActionListener((evt) -> {
            // Update the Devis object with the new values
            // ...

            // Save the changes to the server
           

            // Go back to the previous form
            prevForm.showBack();
        });

        
        addAll(description, quantite, unitprice, saveButton);
    }
}
