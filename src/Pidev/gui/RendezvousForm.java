package Pidev.gui;

import Pidev.entities.RendezVous;
import Pidev.services.ServiceRendezvous;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RendezvousForm extends Form {
    private Form current;

    public RendezvousForm(Resources theme) {
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);

        tb.setTitle("Ajouter Un Rendezvous");
        getContentPane().setScrollVisible(true);

        /*AJOUT*/

        Container addContainer = new Container(new FlowLayout(CENTER, CENTER));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button listBtn = new Button("Consulter Offre");
        Button ajouterBtn = new Button("Ajouter");

        TextField lieuTextField = new TextField("", "Lieu");
        TextField dateTextField = new TextField("", "Date (yyyy-MM-dd)");

        addContainer.add(lieuTextField);
        addContainer.add(dateTextField);

        btnContainer.add(ajouterBtn);
        btnContainer.add(listBtn);
        btnContainer.setLayout(BoxLayout.y());

        ajouterBtn.addActionListener(evt -> {
            try {
                String lieu = lieuTextField.getText();
                String dateStr = dateTextField.getText();
                if (lieu.isEmpty() || dateStr.isEmpty()) {
                    Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = formatter.parse(dateStr);

                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();

                    RendezVous rendezVous = new RendezVous();
                    rendezVous.setLieu(lieu);
                    rendezVous.setDate(date);

                    ServiceRendezvous.getInstance().ajoutRendezvous(rendezVous);

                    iDialog.dispose();
                    refreshTheme();
                    Dialog.show("Rendez-vous", "Rendez-vous ajouté avec succès", "Annuler", "OK");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        listBtn.addActionListener(evt -> new RendezvousViewForm(theme).show());

        add(addContainer);
        add(btnContainer);

        /*FIN AJOUT*/

        /*NAVBAR*/
     //   tb.addMaterialCommandToSideMenu("Home", null, evt -> new HomeForm(theme).show());
       // tb.addMaterialCommandToSideMenu("Rendezvous", null, evt -> new RendezvousForm(theme).show());
    }
}
