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
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.entities.MenuCommande;
import static com.mycompany.myapp.services.MenuService.resultOK;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dhia
 */
public class PanierService {
    
    public ArrayList<Menu> menus;
    float total;
    
    public static PanierService instance=null;
    public static boolean resultOK;
    private ConnectionRequest req;

    private PanierService() {
         req = new ConnectionRequest();
    }

    public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }
        return instance;
    }
    
    public boolean addPanier(MenuCommande mc, int id) {
        System.out.println(mc);
        System.out.println("********");
       String url = Statics.BASE_URL + "/addPanierJSON/" + mc.getMenu_id() + "/" + id;
      
       req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public ArrayList<Menu> parseMenus(String jsonText){
        try {
            menus=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> menusListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)menusListJson.get("root");
            for(Map<String,Object> obj : list){
                Menu m = new Menu();
                float id = Float.parseFloat(obj.get("id").toString());
                m.setId((int)id);
                m.setTitre(obj.get("titre").toString());
                m.setDescription(obj.get("description").toString());
                m.setPrix((Float.parseFloat(obj.get("prix").toString())));
                m.setIngredients(obj.get("ingredients").toString());
                m.setCategorie(obj.get("categorie").toString());
                if (obj.get("image")==null)
                    m.setImage("null");
                else
                    m.setImage(obj.get("image").toString());
                menus.add(m);
            }
            
            
        } catch (IOException ex) {
            
        }
        return menus;
    }
    
    public float parseCommandeTotal(String total){
            return Float.parseFloat(total.toString());
    }
    
    public ArrayList<Menu> getAllPanierItems(int id){
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/showPanierJSON/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                menus = parseMenus(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return menus;
    }
    
    public float getTotal(int id){
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/showTotalJSON/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                total = parseCommandeTotal(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return total;
    }
    
    /*public boolean deleteFavorite(Favoris f) {
       String url = Statics.BASE_URL + "/deleteFavoritesJSON/"+ f.getMenu_id() + "/" + f.getUser_id();
    
       req.setUrl(url);

       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }*/
    
}
