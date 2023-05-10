package GUI.Devis;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import Entity.Devis;
import com.codename1.ui.Button;
import com.codename1.uikit.cleanmodern.BaseForm;
import static java.util.concurrent.ThreadLocalRandom.current;

public class ShowDevisForm extends BaseForm {

    public ShowDevisForm(ListDevisForm previous, Resources res, Devis devis) {
        super("Devis Details", BoxLayout.y());

        Button BackButton = new Button("Back");
        // Add Devis details to form
        super.add(new Label("Devis Details"));
        super.add(new Label("ID: " + devis.getId()));
        super.add(new Label("Expert ID: " + devis.getIdExpert()));
        super.add(new Label("Date: " + devis.getDate()));
        super.add(new Label("Total HT: " + devis.getTotalHt()));

        // Add container to hold buttons
        Container buttonsContainer = new Container(BoxLayout.x());

        // Add close button to close the form
        BackButton.addActionListener(e -> {
            // Edit the Devis
            new ListDevisForm(res).show();
        });
         buttonsContainer.addComponent(BackButton);

        buttonsContainer.add(new com.codename1.ui.Button("Edit"));
        super.add(buttonsContainer);
    }
}
