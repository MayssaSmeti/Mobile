/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.mycompany.entities.Constat;
import com.mycompany.utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


/**
 *
 * @author USER
 */
public class ServiceConstat {
    
    public static ServiceConstat instance = null;
       
    public static boolean resultOk = true;

    
    private ConnectionRequest req;
    
    
    public static ServiceConstat getInstance(){
        if(instance == null )
            instance = new ServiceConstat();
        return instance;
        
    }
    private ArrayList<Constat> constats;
    
    
    public ServiceConstat(){
        
        req = new ConnectionRequest();
    }
    
    public void ajoutConstat(Constat constat){
        
        String url = Statics.BASE_URL+"/constat/addConstatJSON/new?nomclient_e="+constat.getNomclient_e()+"&prenomclient_e="+constat.getPrenomclient_e()+"&typevehicule_e="+constat.getTypevehicule_e()+"&marquevehicule_e="+constat.getMarquevehicule_e()+"&assuranceclient_e="+constat.getAssuranceclient_e()+"&adresseclient_e="+"&emplacementaccid="+constat.getEmplacementaccid()+"&photoaccid="+constat.getPhotoaccid()+"&descriptiondegat="+constat.getDescriptiondegat()+"&observations="+constat.getObservations()+"&numcontrat_e="+constat.getNumcontrat_e()+"&mail="+constat.getMail()+"&Date_creation="+constat.getDate_creation();
        req.setUrl(url);
        req.setPost(false);
        System.out.print(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     
      
    public boolean deleteConstat(int id) {
        String url = Statics.BASE_URL +"/constat/deletedConstatJSON/"+id+"?";        
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
    
        public boolean modifierConstat(Constat constat) {
        String url = Statics.BASE_URL +"/constat/updateConstatJSON/"+constat.getId()+"?"+"nomclient_e="+constat.getNomclient_e()+"&prenomclient_e="+constat.getPrenomclient_e()+"&typevehicule_e="+constat.getTypevehicule_e()+"&marquevehicule_e="+constat.getMarquevehicule_e()+"&assuranceclient_e="+constat.getAssuranceclient_e()+"&adresseclient_e="+constat.getAdresseclient_e()+"&emplacementaccid="+constat.getEmplacementaccid()+"&photoaccid="+constat.getPhotoaccid()+"&descriptiondegat="+constat.getDescriptiondegat()+"&observations="+constat.getObservations()+"&numcontrat_e="+constat.getNumcontrat_e()+"&mail="+constat.getMail()+"&Date_creation="+constat.getDate_creation();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
        
    }

    public ArrayList<Constat> affichageConstats() {
        String url = Statics.BASE_URL + "/constat/AllConstat/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                constats = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        System.out.println(constats);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return constats;
    }
        
    
    
    
    public ArrayList<Constat> parseTasks(String jsonText) {
        try {
            constats = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Constat c = new Constat();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                c.setNomclient_e(obj.get("nomclient_e").toString());
                
                c.setPrenomclient_e(obj.get("prenomclient_e").toString());
                c.setTypevehicule_e(obj.get("typevehicule_e").toString());
                c.setMarquevehicule_e(obj.get("marquevehicule_e").toString());
                c.setAssuranceclient_e(obj.get("assuranceclient_e").toString());
                c.setAdresseclient_e(obj.get("adresseclient_e").toString());
                c.setEmplacementaccid(obj.get("emplacementaccid").toString());
                c.setPhotoaccid(obj.get("marquevehicule_e").toString());
                c.setDescriptiondegat(obj.get("descriptiondegat").toString());
                c.setObservations(obj.get("observations").toString());
                c.setNumcontrat_e(obj.get("numcontrat_e").toString());
                c.setMail(obj.get("mail").toString());
                c.setDate_creation(obj.get("Date_creation").toString());
                constats.add(c);
            }
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return constats;
    }
       
    }
    
    
    
    
    
    
    
    

