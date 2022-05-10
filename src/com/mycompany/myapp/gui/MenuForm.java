/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.RIGHT;
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
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.services.FavorisService;
import com.mycompany.myapp.services.MenuService;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class MenuForm extends BaseForm {

    public MenuForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public MenuForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
        
//        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        gui_imageContainer1.add(BorderLayout.CENTER, sl);
//        sl = new ScaleImageLabel(resourceObjectInstance.getImage("bridge.jpg"));
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        gui_imageContainer2.add(BorderLayout.CENTER, sl);

        ArrayList<Menu>list = MenuService.getInstance().getAllMenus();
        //System.out.println(list);
        
        for(Menu m : list) {
            String urlImage ="http://localhost/PIDEV-WEB/public/uploads/"+m.getImage();
            
            EncodedImage enc=EncodedImage.createFromImage(resourceObjectInstance.getImage("load.png"), false);
            URLImage urlim = URLImage.createToStorage(enc, m.getImage(), urlImage , URLImage.RESIZE_SCALE);
            
            showMenu(urlim, m);
        }
        
        installSidemenu(resourceObjectInstance);
        
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_FAVORITE, e -> {
            
            new FavoritesForm(resourceObjectInstance).show();
        });
//        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SHOPPING_CART, e -> {
//            System.out.println("panier");
//        });
        
    }
    
    private void showMenu(Image img, Menu m) {
        int height = Display.getInstance().convertToPixels(30);
        int width = Display.getInstance().convertToPixels (60);
        ImageViewer imgV=new ImageViewer(img.fill(width, height));
        Container cnt = BorderLayout.north(imgV);
        Label lbTitre = new Label("Titre : "+m.getTitre(),"NewsTopLine2");
        Label lbDescription = new Label("Description : "+m.getDescription(),"NewsTopLine2");
        Label lbPrix = new Label("Prix : "+m.getPrix(),"NewsTopLine2");
        Label lbCategorie = new Label("Catégorie : "+m.getCategorie(),"NewsTopLine2");
        Button btnAddMenu = new Button("Ajouter au panier");
        
        Label lbFavorite = new Label(" ");
        lbFavorite.setUIID ("NewsTopLine");
        Style favoriteStyle = new Style (lbFavorite.getUnselectedStyle());
        favoriteStyle.setFgColor(0xf21f1f);
        FontImage favoriteImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE_BORDER, favoriteStyle);
        FontImage favoriteImageFull = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, favoriteStyle);
        lbFavorite.setIcon(favoriteImage);
        lbFavorite.setTextPosition (RIGHT);
        
        
        lbFavorite.addPointerPressedListener(l->{
           //System.out.println("favoris");
            Favoris f = new Favoris(m.getId(),1) ;
                        if( FavorisService.getInstance().addFavorite(f))
                        {
                           Dialog.show("Success","Menu ajouté a votre liste de favoris!",new Command("OK"));
                           lbFavorite.setIcon(favoriteImageFull);
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
        });
      
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(lbTitre,lbFavorite), BoxLayout.encloseX(lbDescription), BoxLayout.encloseX(lbPrix) , BoxLayout.encloseX(lbCategorie), BoxLayout.encloseY(btnAddMenu)));
        add(cnt);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Menus");
        setName("MenuForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
}
