/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycomany.utils.Statics;
import com.mycompany.gui.SessionManager;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author aziz3
 */
public class ServiveUtilisateur {
    
    public static ServiveUtilisateur instance = null ;

    public static boolean resultOk = true;
    String json;
    
    private ConnectionRequest req;
    
    public static ServiveUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiveUtilisateur();
        return instance ;
    }
    
    public ServiveUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
       
    public void signup(TextField nom, TextField prenom, TextField email, TextField telephone, TextField password, TextField cin, TextField adresse, Resources res) {{
        
        System.out.println(password.getText());
        
      String url = Statics.BASE_URL + "/signup?nom=" + nom.getText() +
        "&prenom=" + prenom.getText() +
        "&num_tel=" + telephone.getText() +
        "&email=" + email.getText() +
        "&password=" + password.getText() +
        "&cin=" + cin.getText() +
        "&adresse=" + adresse.getText();
        
        req.setUrl(url);
       
        //Control saisi
        if(nom.getText().equals(" ")&& prenom.getText().equals(" ") && cin.getText().equals(" ")&& telephone.getText().equals(" ")&& password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
//        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData(); 
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    }
    
    
    public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/signin?email="+email.getText()+"&password="+password.getText();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("user not found")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setPrenom(user.get("prenom").toString());                
                SessionManager.setEmail(user.get("email").toString());
                
//                //photo 
//                
//                if(user.get("photo") != null)
//                    SessionManager.setImage(user.get("image").toString());
                
                
                if(user.size() >0 ) 
                   // houni nzid user win yemchi baed ma yamel login
                    System.out.println("helloooooo");
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    public String getPasswordByEmail(String email, Resources res ) {
        
        
        String url = Statics.BASE_URL+"/mobile/updatepassword?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    
        return json;
    }
}
