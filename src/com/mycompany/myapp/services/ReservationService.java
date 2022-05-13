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
import entities.Reservation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author USER
 */
public class ReservationService {
     public static ReservationService instance = null;
   
    private ConnectionRequest req;
    
    public static ReservationService getInstance() {
        if (instance == null) 
            instance = new ReservationService();
        
        return instance;
        }
    
    public ReservationService() {
        req = new ConnectionRequest();
        
    }
    
     public boolean addReservation(Reservation reservation) {
         System.out.println(reservation); 
        System.out.println("********");
        String url = Statics.BASE_URL +"/ajouterreservation/{id}?nom="+reservation.getNom()+"&nbrPersonnes=" +reservation.getNbr_personnes();
        //String url = Statics.BASE_URL + "create";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("nom",reservation.getNom());
        req.addArgument("evenement_id",reservation.getEvenement_id()+"");
        req.addArgument("nombre personnes",reservation.getNbr_personnes()+"");
        req.addArgument("user_id",reservation.getUser_id()+"");


        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                boolean resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if (resultOK) {
                    Dialog.show("sucess", "reservation ajoutée", null, "OK");
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
       public ArrayList<Reservation> affichageReservation(){
        
        ArrayList<Reservation> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/affichereservation";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
            Map<String,Object>mapReservations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapReservations.get("root");
            for(Map<String,Object> obj : listOfMaps){
                Reservation r = new Reservation();
                float id = Float.parseFloat(obj.get("id").toString());
                String Nom = obj.get("Nom").toString();
                String Evenementid = obj.get("evenement_id").toString();
                String Nbrpersonnes = obj.get("nombre personnes").toString();
                String Userid = obj.get("user_id").toString();

                 
                r.setId((int)id);
                r.setNom(obj.get("nom").toString());
                r.setEvenement_id((int)obj.get("evenement_id"));
                r.setNbr_personnes((int)obj.get("nombre personnes"));             
                r.setUser_id((int)obj.get("user_id"));
    
                result.add(r);
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
        public boolean supprimerReservation(int id) {
        
        System.out.println("********");
        String url = Statics.BASE_URL +"/deletereservationmobile?id="+id;

        req.setUrl(url);


        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                boolean resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if (resultOK || req.getResponseCode() == 202) {
                    Dialog.show("sucess", "reservation supprimé", null, "OK");
                   
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
        public boolean updateReservation(Reservation reservation) {
        System.out.println(reservation);
        System.out.println("********");
        String url = Statics.BASE_URL +"/updatereservation?id="+reservation.getId()+"&nom=" + reservation.getNom() + "&nbrPersonnes=" + reservation.getNbr_personnes();
              
       //String url = Statics.BASE_URL + "create";
    
       req.setUrl(url);
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                boolean resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if (resultOK || req.getResponseCode() == 202) {
                    Dialog.show("sucess", "reservation modifié", null, "OK");
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
     
}
