/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Devis;

import Entity.Devis;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import service.ServiceDevis;

/**
 *
 * @author SCORPIO-12
 */
public class ListDevisForm extends BaseForm {

    public ListDevisForm(Resources res) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
        Toolbar tb = new Toolbar(true);
        ListDevisForm current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List devis");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        
        tb.addSearchCommand(e ->  {
            
        });
        
        ArrayList<Entity.Devis> devi = ServiceDevis.getInstance().getAllDevis();
        Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
         
         for (Entity.Devis devis : devi) {
    MultiButton sp = new MultiButton();
    sp.setTextLine1("id : "+devis.getId());
    sp.setTextLine2("id_expert : "+devis.getIdExpert());
    sp.setTextLine3(" date : "+devis.getDate());
    sp.setTextLine4("TotalHt : "+devis.getTotalHt());

    // Create a new container for each Devis that contains the MultiButton and the buttons for each action
    Container devisContainer = new Container(new BorderLayout());
    devisContainer.addComponent(BorderLayout.CENTER, sp);

    // Create the buttons for each action
    Button showButton = new Button("Show");
    Button editButton = new Button("Edit");
    Button deleteButton = new Button("Delete");

    // Add an ActionListener to each button to perform the corresponding action
  showButton.addActionListener(e -> {
    // Show the details of the Devis
    System.out.println("Showing Devis " + devis.getId());
    new ShowDevisForm(current, res, devis).show(); // create and show new form to display Devis details
});
    editButton.addActionListener(e -> {
        // Edit the Devis
         EditForm editForm = new EditForm(current, res, devis);
    });
  deleteButton.addActionListener(e -> {
        boolean isDeleted = ServiceDevis.getInstance().deletedDevis(devis.getId());
        if (isDeleted) {
            // Devis is deleted successfully, remove the Devis from the UI
            devisContainer.remove();
        } else {
            // Error deleting Devis, show an error message to the user
            Dialog.show("Error", "Failed to delete Devis", "OK", null);
        }
    });

    // Add the buttons to the container
    Container buttonsContainer = new Container(new GridLayout(1, 3));
    buttonsContainer.addComponent(showButton);
    buttonsContainer.addComponent(editButton);
    buttonsContainer.addComponent(deleteButton);
    devisContainer.addComponent(BorderLayout.SOUTH, buttonsContainer);

    list.add(devisContainer);
}

this.add(list);

       
}
}

         
      
