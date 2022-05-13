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
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Favoris;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.MenuCommande;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.EvenementService;
import com.mycompany.myapp.services.FavorisService;
import com.mycompany.myapp.services.MenuService;
import com.mycompany.myapp.services.PanierService;
import com.mycompany.myapp.services.ReservationService;
import com.mycompany.myapp.services.UserService;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class EventForm extends BaseForm {

    public EventForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public EventForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
        
//        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        gui_imageContainer1.add(BorderLayout.CENTER, sl);
//        sl = new ScaleImageLabel(resourceObjectInstance.getImage("bridge.jpg"));
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        gui_imageContainer2.add(BorderLayout.CENTER, sl);

        ArrayList<Evenement>list = EvenementService.getInstance().getAllEvents();
        
        for(Evenement event : list) {
            String urlImage ="http://localhost/PIDEV-WEB/public/uploads/"+event.getImage();
            
            EncodedImage enc=EncodedImage.createFromImage(resourceObjectInstance.getImage("load.png"), false);
            URLImage urlim = URLImage.createToStorage(enc, event.getImage(), urlImage , URLImage.RESIZE_SCALE);
            
            showEvent(urlim,event);
        }
        
        installSidemenu(resourceObjectInstance);
        
        
        
    }
    
    private void showEvent(Image img,Evenement Event) {
        int height = Display.getInstance().convertToPixels(30);
        int width = Display.getInstance().convertToPixels (60);
        ImageViewer imgV=new ImageViewer(img.fill(width, height));
        Container cnt = BorderLayout.north(imgV);
        Label lbnom = new Label("nom : "+Event.getNom(),"NewsTopLine2");
        Label lbdate = new Label("date : "+Event.getDate(),"NewsTopLine2");
        Label lbcategorie = new Label("categorie : "+Event.getCategorie(),"NewsTopLine2");
        Label lbdescription = new Label("Description : "+Event.getDescription(),"NewsTopLine2");
        Button btnReserver = new Button("Reserver");
        
        btnReserver.addActionListener(p->{
            Reservation reser = new Reservation(UserService.userconnected.getNom(),Event.getId(),1,UserService.userconnected.getId()) ;
                        if( ReservationService.getInstance().addReservation(reser))
                        {
                           Dialog.show("Success","Reservation ajoutée",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
        });
        
        
       
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(lbnom), BoxLayout.encloseX(lbdate) ,BoxLayout.encloseX(lbcategorie) ,BoxLayout.encloseX(lbdescription), BoxLayout.encloseY(btnReserver)));
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
        setTitle("Events");
        setName("EventForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
}
