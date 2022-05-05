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
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dhia
 */
public class MenuService {
    
    public ArrayList<Menu> menus;
    
    public static MenuService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private MenuService() {
         req = new ConnectionRequest();
    }

    public static MenuService getInstance() {
        if (instance == null) {
            instance = new MenuService();
        }
        return instance;
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
                m.setImage(obj.get("image").toString());
                menus.add(m);
            }
            
            
        } catch (IOException ex) {
            
        }
        return menus;
    }
    
    public ArrayList<Menu> getAllMenus(){
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/showMenusJSON";
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
    
}
