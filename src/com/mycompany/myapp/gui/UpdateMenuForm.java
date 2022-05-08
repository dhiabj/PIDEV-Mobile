/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Switch;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.services.MenuService;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author bhk
 */
public class UpdateMenuForm extends Form{
    Form current;
    private String image;

    public UpdateMenuForm(Form previous, Resources res, Menu m) {
        int id = m.getId();
        setTitle("Modifier menu");
        setLayout(BoxLayout.y());
        
        TextField tfTitre = new TextField(m.getTitre(),"Titre");
        TextField tfDescription= new TextField(m.getDescription(), "Description");
        TextField tfPrix= new TextField(m.getPrix()+"", "Prix");
        Container cnt=new Container(BoxLayout.x());
        Label lbCategorie=new Label();
        Label lb=new Label("Vegan :");
        Switch sw=new Switch();
        if("Vegan".equals(m.getCategorie())){
            sw.setOn();
        }
        cnt.addAll(lb,sw);
        Button btnValider = new Button("Modifier menu");
        Button btCapture = new Button("Choisir une image");
        Label lbImage = new Label();
        btCapture.addActionListener(l->{
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(".jpg, .jpeg, png/plain", e2-> {
                String file = (String)e2.getSource();
                if (file == null) {
                    add("No file was selected");
                    revalidate();
                } else {
                   int index = file.lastIndexOf("/");
                   image = file.substring(index + 1);
                   lbImage.setText(image);
                   //add("Selected file "+file);
                   Image previewImg;
                   try{
                      previewImg = Image.createImage(file).scaledHeight(500);
                      add(previewImg);
                      String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";
                      try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)){
                          System.out.println(imageFile);
                          ImageIO.getImageIO().save(previewImg, os, ImageIO.FORMAT_PNG, 1);
                      } catch(IOException err){
                          
                      }
                   } catch (IOException ex) {
                        
                    }
                }
                //System.out.println(image);
                revalidate();
    });
}});
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length()==0)||(tfDescription.getText().length()==0) ||(tfPrix.getText().length()==0) ||(lbImage.getText().length()==0))
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        if(sw.isValue())
                            lbCategorie.setText("Vegan");
                        else{
                            lbCategorie.setText("Normal");
                        }
                        Menu m = new Menu(id, tfTitre.getText(), 
                                tfDescription.getText(), Float.parseFloat(tfPrix.getText()), lbCategorie.getText(), lbImage.getText());
                        if( MenuService.getInstance().updateMenu(m))
                        {
                           Dialog.show("Success","Menu modifié avec succès!",new Command("OK"));
                           new ListMenusForm(previous, res).show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Prix doit être un nombre", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfTitre,tfDescription,tfPrix,btCapture);
        addAll(cnt,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
