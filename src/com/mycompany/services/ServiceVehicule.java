/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Vehicule;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author USER
 */
public class ServiceVehicule {
    
    public static ServiceVehicule instance = null;
       
    public static boolean resultOk = true;
    private ArrayList<Vehicule> vehicules;

    public ConnectionRequest req;
    
    public static ServiceVehicule getInstance(){
        if(instance == null )
            instance = new ServiceVehicule();
        return instance;
        
    }
    
    public ServiceVehicule(){
        
        req = new ConnectionRequest();
    }
        public boolean ajoutVehicule(Vehicule  vehicule) {
        String matricule = vehicule.getMatricule();
        String type = vehicule.getType();
        String marque = vehicule.getMarque();
        String nb_ch = vehicule.getNb_ch();
        System.out.println(vehicule.toString());
        String url = Statics.BASE_URL + "/vehicule/addVehiculeJSON/new?matricule=" + matricule + "&type="+  type +"&marque=" + marque +"&nb_ch="+ nb_ch;
        req.setUrl(url);
        req.setPost(true);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                boolean resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
  
    public boolean deleteVehicule(int id) {
      String url = Statics.BASE_URL +"/vehicule/deletedVehiculeJSON/"+id+"?";        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;    }

    public ArrayList<Vehicule> affichageVehicules() {
        String url = Statics.BASE_URL + "/vehicule/AllVehicule/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                vehicules = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        System.out.println(vehicules);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return vehicules;
    }

        public ArrayList<Vehicule> parseTasks(String jsonText) {
        try {
            vehicules = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Vehicule v = new Vehicule();
                float id = Float.parseFloat(obj.get("id").toString());
                v.setId((int) id);
                v.setMatricule(obj.get("matricule").toString());
                v.setType(obj.get("type").toString());
                v.setMarque(obj.get("marque").toString());
                v.setNb_ch(obj.get("nb_ch").toString());
                vehicules.add(v);
            }
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return vehicules;
    }
    public boolean modifierVehicule(Vehicule v) {
    String url = Statics.BASE_URL +"/vehicule/updateVehiculeJSON/"+v.getId()+"?"+"matricule="+v.getMatricule()+"&type="+v.getType()+"&marque="+v.getMarque()+"&nb_ch="+v.getNb_ch();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;    }
    
}
