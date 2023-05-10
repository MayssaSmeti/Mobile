/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author SCORPIO-12
 */
import Entity.Devis;
import Entity.DevisItem;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceDevis {

    private static ServiceDevis instance = null;
    public ConnectionRequest req;
    public ArrayList<DevisItem> DeviItem;
    public ArrayList<Devis> Devi;

    public boolean resultOK;

    public static ServiceDevis getInstance() {
        if (instance == null) {
            instance = new ServiceDevis();
        }
        return instance;
    }

    private ServiceDevis() {
        req = new ConnectionRequest();
    }

    public boolean AddDevisItem(DevisItem DevisItem) {
        String url = Statics.BASE_URL + "/addDevisJSON/new?description=" + DevisItem.getDescription() + "&quantite=" + DevisItem.getQuantite() + "&unitprice=" + DevisItem.getUnitprice();
        // String url = Statics.BASE_URL+"/addDevisJSON/new?description=fghhdh&quantite=5&unitprice=8";
        //  String url = Statics.BASE_URL + "create";
        req.setUrl(url);
        req.addResponseListener((e) -> {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(req.getResponseData());
            System.out.println("data" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Devis> parseTasks(String jsonText) {
        try {
            Devi = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Devis t = new Devis();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setTotalHt(Float.parseFloat(obj.get("totalHt").toString()));
                Devi.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Devi;
    }

    public ArrayList<Devis> getAllDevis() {
        String url = Statics.BASE_URL + "/allDevis";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Devi = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Devi;
    }

    public boolean deletedDevis(int id) {

        String url = Statics.BASE_URL + "/deleteDevisJSON/" + id + "";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updateDevis(Devis Devis) {
        String url = Statics.BASE_URL + "/updateDevisJSON/" + Devis.getId() + "&id_expert=" + Devis.getIdExpert() + "&total_ht=" + Devis.getTotalHt()+"&date="+Devis.getDate();
        //  String url = Statics.BASE_URL + "create";
        req.setUrl(url);
        req.addResponseListener((e) -> {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(req.getResponseData());
            //System.out.println("data"+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
