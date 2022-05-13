/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
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
import com.mycompany.myapp.entities.ReclamationUser;
import com.mycompany.myapp.services.FavorisService;
import com.mycompany.myapp.services.ReclamationUserService;
import com.mycompany.myapp.services.UserService;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ReclamationForm extends Form {
    Form current;

    public ReclamationForm(Resources res) {
        setTitle("Mes Reclamations");
        Button btnRec = new Button("Envoyer reclamation");
        btnRec.addActionListener(l->{
            new AddRecForm(res).show();
        });

//        SpanLabel sp = new SpanLabel();
//        sp.setText(MenuService.getInstance().getAllMenus().toString());
//        add(sp);

        ArrayList<ReclamationUser>list = ReclamationUserService.getInstance().getAllRec(UserService.userconnected.getId());
        //System.out.println(list);
        
        for(ReclamationUser rec : list) {
            showRec(rec, res);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            new MenuForm().showBack();
        });
        add(btnRec);
    }

    private void showRec(ReclamationUser rec, Resources res) {
        int height = Display.getInstance().convertToPixels(30);
        int width = Display.getInstance().convertToPixels (60);
        
        
      
        Label lbTitre = new Label("Titre : "+rec.getTitre(),"NewsTopLine2");
        Label lbTexte = new Label("Texte : "+rec.getTexte(),"NewsTopLine2");
        Label lb = new Label();
        Container cnt = BorderLayout.north(lb);
       
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
                
                if(ReclamationUserService.getInstance().deleteRec(rec.getIdrep())){
                    new ReclamationForm(res).show();
                }
            }
        });
        
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(lbTitre, lbDelete), BoxLayout.encloseX(lbTexte)));
        add(cnt);
    }

}
