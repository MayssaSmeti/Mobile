package Pidev.services;

import Pidev.entities.RendezVous;
import Pidev.utils.MyConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author rayen
 */
public class ServiceRendezvous {

    //singleton 
    public static ServiceRendezvous instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceRendezvous getInstance() {
        if (instance == null) {
            instance = new ServiceRendezvous();
        }
        return instance;
    }

    public ServiceRendezvous() {
        req = new ConnectionRequest();

    }

    //ajout 
    public void ajoutRendezvous(RendezVous rendezvous) {

        String url = MyConnection.BASE_URL + "/addRendezVousJSON?lieu=" + rendezvous.getLieu() + "&date=" + rendezvous.getDate(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

    }

    //affichage
    public ArrayList<RendezVous> affichageRendezvous() {
        ArrayList<RendezVous> result = new ArrayList<>();

        String url = MyConnection.BASE_URL + "/AllRDV";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapRendezvous = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapRendezvous.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        RendezVous re = new RendezVous();

                        //dima id fi codename one float 5outhouha
                        String objet = obj.get("lieu").toString();

                        re.setLieu(objet);

                        //Date 
                        String dateStr = obj.get("date").toString();
                        long timestamp = Long.parseLong(dateStr.substring(dateStr.indexOf("timestamp") + 10, dateStr.lastIndexOf("}")));
                        Date date = new Date(timestamp * 1000);

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(date);
                        re.setDate(date);
                        //insert data into ArrayList result
                        result.add(re);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }

    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    /*public Rendezvous DetailRecalamation( int id , Rendezvous reclamation) {
        
        String url = Statics.BASE_URL+"/detailReclamation?"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                reclamation.setLieu(obj.get("obj").toString());
          
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return reclamation;
        
        
    }
     */
    //Delete 
    public boolean deleteRendezvous(int id) {
        String url = MyConnection.BASE_URL + "/deleteRDV?id=" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    //Update 
    public boolean modifierRendzevous(RendezVous rendezvous) {
        String url = MyConnection.BASE_URL + "/updateRendezvous?date=" + rendezvous.getDate() + "&lieu=" + rendezvous.getLieu();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }

}
