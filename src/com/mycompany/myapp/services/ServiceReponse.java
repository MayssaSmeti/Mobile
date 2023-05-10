/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.myapp.entities.Reponse;
/**
 *
 * @author msi
 */
public class ServiceReponse {
    
     //singleton
    public static ServiceReponse instance = null;
    
    private ConnectionRequest req;
    
    public static ServiceReponse getInstance(){
        if(instance == null)
            instance = new ServiceReponse();
        return instance;
    }
    
    public ServiceReponse(){
        req = new ConnectionRequest();
    }
    
    public void AjouterReponse(Reponse rep){
        
        String description = rep.getDescription();
        
        //String url=Statics.BASE_URL+"reponse/addReponseJSON/new?description="+rep.getDescription();

        String url= Statics.BASE_URL+"reponse/addReponseJSON/new?description="+rep.getDescription();
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e)->{
           
            String str = new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
}
