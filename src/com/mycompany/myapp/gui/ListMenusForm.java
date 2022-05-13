/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.services.MenuService;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListMenusForm extends Form {
    private Resources theme;
    Form current;

    public ListMenusForm(Form previous, Resources res) {
        setTitle("Menu Liste");

//        SpanLabel sp = new SpanLabel();
//        sp.setText(MenuService.getInstance().getAllMenus().toString());
//        add(sp);

        ArrayList<Menu>list = MenuService.getInstance().getAllMenus();
        //System.out.println(list);
        
        for(Menu m : list) {
            String urlImage ="http://localhost/PIDEV-WEB/public/uploads/"+m.getImage();
            
            EncodedImage enc=EncodedImage.createFromImage(res.getImage("load.png"), false);
            URLImage urlim = URLImage.createToStorage(enc, m.getImage(), urlImage , URLImage.RESIZE_SCALE);
            
            showMenu(urlim, m, previous, res);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void showMenu(Image img, Menu m,Form previous, Resources res) {
        int height = Display.getInstance().convertToPixels(30);
        int width = Display.getInstance().convertToPixels (60);
        ImageViewer imgV=new ImageViewer(img.fill(width, height));
        Container cnt = BorderLayout.north(imgV);
        Label lbTitre = new Label("Titre : "+m.getTitre(),"NewsTopLine2");
        Label lbDescription = new Label("Description : "+m.getDescription(),"NewsTopLine2");
        Label lbIngredients = new Label("Ingrédients : "+m.getIngredients(),"NewsTopLine2");
        Label lbPrix = new Label("Prix : "+m.getPrix(),"NewsTopLine2");
        Label lbCategorie = new Label("Catégorie : "+m.getCategorie(),"NewsTopLine2");
       
        Label lbDelete = new Label(" ");
        lbDelete.setUIID ("NewsTopLine");
        Style deleteStyle = new Style (lbDelete.getUnselectedStyle());
        deleteStyle.setFgColor(0xf21f1f);
        FontImage deleteImage = FontImage.createMaterial (FontImage. MATERIAL_DELETE, deleteStyle); 
        lbDelete.setIcon (deleteImage);
        lbDelete.setTextPosition (RIGHT);
        
        
        lbDelete.addPointerPressedListener(l->{
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce menu?", "Annuler","Oui")){
                dig.dispose();
            }
            else{
                dig.dispose();
                
                if(MenuService.getInstance().deleteMenu(m.getId())){
                    new ListMenusForm(previous, res).show();
                }
            }
        });
        
        Label lbUpdate = new Label(" ");
        lbUpdate.setUIID ("NewsTopLine");
        Style updateStyle = new Style (lbUpdate.getUnselectedStyle());
        updateStyle.setFgColor(0xf7ad02);
        FontImage updateImage = FontImage.createMaterial (FontImage. MATERIAL_EDIT, updateStyle); 
        lbUpdate.setIcon (updateImage);
        lbUpdate.setTextPosition (RIGHT);
        
        lbUpdate.addPointerPressedListener(l->{
            new UpdateMenuForm(previous, res, m).show();
        });
        
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(lbTitre, lbDelete, lbUpdate), BoxLayout.encloseX(lbDescription), BoxLayout.encloseX(lbPrix) , BoxLayout.encloseX(lbIngredients) , BoxLayout.encloseX(lbCategorie)));
        add(cnt);
    }

}
