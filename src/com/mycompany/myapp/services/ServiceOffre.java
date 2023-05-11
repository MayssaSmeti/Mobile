/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.utils.Statics;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author zaghd
 */
public class ServiceOffre {
    
    //singleton
    public static ServiceOffre instance = null;
    
    private ConnectionRequest req;
    
    public ArrayList<Offre> offs ;
    
    public static boolean resultOk = true;
    
    public static ServiceOffre getInstance(){
        if(instance == null)
            instance = new ServiceOffre();
        return instance;
    }
    
    public ServiceOffre(){
        req = new ConnectionRequest();
    }
    
    public void AjouterOffre(Offre offre){
        
        String url=Statics.BASE_URL+"AddOffres?description="+offre.getDescription()+
                "&titre="+offre.getTitre()+
                "&prix="+offre.getPrix()+
                "&validite_offre="+offre.getValidite_offre()+
                "&image_offre="+offre.getImage_offre();
        
        req.setUrl(url);
        req.addResponseListener((e)->{
           
            String str = new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
     public ArrayList<Offre> parseTasks(String jsonText) {
        offs = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> offreListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) offreListJson.get("root");
            //System.out.println("la liste est"+ list);
            for (Map<String, Object> obj : list) {
                //System.out.println("la liste est :"+ list);
                Offre t = new Offre();
                
                float id = Float.parseFloat(obj.get("id").toString());
                ///System.out.println("l'id de offre est :"+ id);
                t.setId((int)id);
                t.setDescription(obj.get("description").toString());
                //System.out.println("t est devenue"+ t);
                float price = Float.parseFloat(obj.get("prix").toString());
                t.setPrix((int)price);
                t.setTitre("/" + obj.get("titre").toString());
                t.setValidite_offre("/" + obj.get("validte_offre").toString());
                t.setImage_offre(obj.get("image_offre").toString());
                //System.out.println("offre" + t.toString());
                offs.add(t);
                System.out.println("la liste des offres est"+offs);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return offs;
    }
     
    //Affichage des Offres
    public ArrayList<Offre>AfficherOffre(){
        String url = Statics.BASE_URL+"AllOffres";
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                offs = parseTasks(new String(request.getResponseData()));
                request.removeResponseListener(this);
                System.out.println("bellehi emchi ekhdm"+offs);
            }
        });
        //NetworkManager.getInstance().addToQueueAndWait(request);

        return offs;
    }
    
    
    
    
    //Suppression Offre
    public boolean SupprimerOffre(int id ) {
        String url = Statics.BASE_URL +"#"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    //Modification Offre
    public boolean ModifierOffre(Offre off) {
        String url = Statics.BASE_URL +"#"+off.getId()+"?description="+off.getDescription()+
                "&titre="+off.getTitre()+
                "&prix="+off.getPrix()+
                "&validite_offre="+off.getValidite_offre()+
                "&image_offre="+off.getImage_offre();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;
        
    }
   
    
 
    
    
    
    
}
