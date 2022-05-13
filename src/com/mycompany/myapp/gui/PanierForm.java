/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
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
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.entities.MenuCommande;
import com.mycompany.myapp.services.FavorisService;
import com.mycompany.myapp.services.MenuService;
import com.mycompany.myapp.services.PanierService;
import com.mycompany.myapp.services.UserService;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class PanierForm extends Form {
    Form current;

    public PanierForm(Resources res) {
        setTitle("Panier");

//        SpanLabel sp = new SpanLabel();
//        sp.setText(MenuService.getInstance().getAllMenus().toString());
//        add(sp);

        ArrayList<Menu>list = PanierService.getInstance().getAllPanierItems(1);
        ArrayList<Commande>commande = PanierService.getInstance().getTotalPanier(1);
        //System.out.println(Total.get(0).getId());
        int commandId = commande.get(0).getId();
        float total = commande.get(0).getTotal();
        //System.out.println(list);
        
        for(Menu m : list) {
            String urlImage ="http://localhost/PIDEV-WEB/public/uploads/"+m.getImage();
            
            EncodedImage enc=EncodedImage.createFromImage(res.getImage("load.png"), false);
            URLImage urlim = URLImage.createToStorage(enc, m.getImage(), urlImage , URLImage.RESIZE_SCALE);
            
            showMenu(urlim, m, res, commandId);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            new MenuForm().showBack();
        });
        
        Label lbTotal = new Label("Totale : "+total,"NewsTopLine2");
        Button btnValider = new Button("Valider");
        addAll(lbTotal,btnValider);
        
        btnValider.addActionListener(v->{
            if( PanierService.getInstance().validerPanier(UserService.userconnected.getId()))
                        {
                           Dialog.show("Success","Panier validé avec success!",new Command("OK"));
                           new MenuForm(res).showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
        });
    }

    private void showMenu(Image img, Menu m, Resources res, int commandId) {
        int height = Display.getInstance().convertToPixels(30);
        int width = Display.getInstance().convertToPixels (60);
        ImageViewer imgV=new ImageViewer(img.fill(width, height));
        Container cnt = BorderLayout.north(imgV);
        Label lbTitre = new Label("Titre : "+m.getTitre(),"NewsTopLine2");
        Label lbDescription = new Label("Description : "+m.getDescription(),"NewsTopLine2");
        Label lbPrix = new Label("Prix : "+m.getPrix(),"NewsTopLine2");
       
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
                
                MenuCommande mc = new MenuCommande(m.getId(),commandId);
                if(PanierService.getInstance().deleteFromPanier(mc)){
                    new PanierForm(res).show();
                }
            }
        });
        
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(lbTitre, lbDelete), BoxLayout.encloseX(lbDescription), BoxLayout.encloseX(lbPrix)));
        add(cnt);
    }

}
