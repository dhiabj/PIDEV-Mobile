/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionListener;
import entities.Evenement;
import utils.Statics;
import com.codename1.ui.Dialog;
import com.codename1.io.NetworkManager;
import com.codename1.io.JSONParser;
import com.codename1.io.CharArrayReader;
import com.codename1.l10n.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author USER
 */
public class EvenementService {
    public static EvenementService instance = null;
   
    private ConnectionRequest req;
    
    public static EvenementService getInstance() {
        if (instance == null) 
            instance = new EvenementService();
        
        return instance;
        }
    
    public EvenementService() {
        req = new ConnectionRequest();
        
    }
     public boolean addEvent(Evenement evenement) {
         System.out.println(evenement); 
        System.out.println("********");
        String url = Statics.BASE_URL +"/addeventmobile?nom="+evenement.getNom()+"&description=" +evenement.getDescription()+"&date="+evenement.getDate()+"&image="+evenement.getImage()+"&nbrPersonnes="+evenement.getNbr_personnes()+"&categorie="+evenement.getCategorie();
        //String url = Statics.BASE_URL + "create";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("nom",evenement.getNom());
         req.addArgument("description",evenement.getDescription());
        req.addArgument("date",evenement.getDate());
        req.addArgument("image",evenement.getImage());
        req.addArgument("nombre personnes",evenement.getNbr_personnes()+"");
        req.addArgument("categorie",evenement.getCategorie());


        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                boolean resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if (resultOK) {
                    Dialog.show("sucess", "evenement ajoutée", null, "OK");
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
      public ArrayList<Evenement> affichageEvenement(){
        
        ArrayList<Evenement> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/afficheevent";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
            Map<String,Object>mapEvenements = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapEvenements.get("root");
            for(Map<String,Object> obj : listOfMaps){
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                String Nom = obj.get("Nom").toString();
                String DateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp")+10,obj.get("obj").toString().lastIndexOf(")"));
                Date currentTime = new Date(Double.valueOf(DateConverter).longValue()*1000);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(currentTime);
                e.setDate(dateString);
                
                String Nbrpersonnes = obj.get("nombre personnes").toString();
                String Categorie = obj.get("categorie").toString();
                String Description = obj.get("description").toString();
                String Image = obj.get("image").toString();
                 
                e.setId((int)id);
                e.setNom(obj.get("nom").toString());
                e.setDate(obj.get("date").toString());
                e.setNbr_personnes((int)obj.get("nombre personnes"));             
                e.setCategorie(obj.get("categorie").toString());
                e.setDescription(obj.get("description").toString());               
                if (obj.get("image")==null)
                    e.setImage("null");
                else
                    e.setImage(obj.get("image").toString());
                result.add(e);
                System.out.println("here ==>"+result.toString());
            }       
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  result;
    }
      
    public boolean supprimerEvent(int id) {
        
        System.out.println("********");
        String url = Statics.BASE_URL +"/deleteeventmobile?id="+id;

        req.setUrl(url);


        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                boolean resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if (resultOK || req.getResponseCode() == 202) {
                    Dialog.show("sucess", "evenement supprimé", null, "OK");
                   
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
     public boolean updateEvenement(Evenement evenement) {
        System.out.println(evenement);
        System.out.println("********");
        String url = Statics.BASE_URL +"/updateevent?id="+evenement.getId()+"&nom=" + evenement.getNom() + "&description=" + evenement.getDescription()
               + "&date=" + evenement.getDate() + "&image=" + evenement.getImage() + "&nbrPersonnes=" + evenement.getNbr_personnes() + "&categorie=" + evenement.getCategorie();
       //String url = Statics.BASE_URL + "create";
    
       req.setUrl(url);
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                boolean resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if (resultOK || req.getResponseCode() == 202) {
                    Dialog.show("sucess", "evenement modifié", null, "OK");
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
}


