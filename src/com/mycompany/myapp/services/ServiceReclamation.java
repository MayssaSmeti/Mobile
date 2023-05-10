/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.mycompany.myapp.utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServiceReclamation {
    
     //singleton
    public static ServiceReclamation instance = null;
     public ArrayList<Reclamation> Reclamations;
    
    public ConnectionRequest req;
    
    public static ServiceReclamation getInstance(){
        if(instance == null)
            instance = new ServiceReclamation();
        return instance;
    }
    
    public ServiceReclamation(){
        req = new ConnectionRequest();
    }
    
    public void AjouterReclamation(Reclamation rec){
        
        String description = rec.getDescription();
        String objet = rec.getObjet();
        int note =  rec.getNote();
        
        String url=Statics.BASE_URL+"reclamation/addReclamationJSON/new?description="+rec.getDescription()+"&objet="+rec.getObjet()+"&note="+rec.getNote();
       // String url = Statics.BASE_URL + "reclamation/addReclamationJSON/new/" + description + "/" + objet+"/"+note;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e)->{
           
            String str = new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);    
    }
    
     public ArrayList<Reclamation> parseTasks(String jsonText) {
        try {
            Reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                System.out.println(t.getId());
                //t.setTotalHt((Float.parseFloat(obj.get("totalHt").toString())));
                Reclamations.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Reclamations;
    }
     
      public ArrayList<Reclamation> getAllReclamation() {
        String url = Statics.BASE_URL + "/reclamation/liste";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reclamations = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reclamations;
    }
}
