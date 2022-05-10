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
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.Menu;
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
public class FavorisService {
    
    public ArrayList<Menu> menus;
    
    public static FavorisService instance=null;
    public static boolean resultOK;
    private ConnectionRequest req;

    private FavorisService() {
         req = new ConnectionRequest();
    }

    public static FavorisService getInstance() {
        if (instance == null) {
            instance = new FavorisService();
        }
        return instance;
    }
    
    public boolean addFavorite(Favoris f) {
        System.out.println(f);
        System.out.println("********");
       String url = Statics.BASE_URL + "/addFavoriteJSON/" + f.getMenu_id() + "/" + f.getUser_id();
      
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
    
    public ArrayList<Menu> getAllFavorites(int id){
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/showFavoritesJSON/"+id;
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
    
    public boolean deleteFavorite(Favoris f) {
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
    }
    
}
