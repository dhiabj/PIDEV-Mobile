/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Switch;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.services.MenuService;

/**
 *
 * @author bhk
 */
public class AddMenuForm extends Form{

    public AddMenuForm(Form previous) {
        setTitle("Ajouter un nouveau menu");
        setLayout(BoxLayout.y());
        
        TextField tfTitre = new TextField("","Titre");
        TextField tfDescription= new TextField("", "Description");
        TextField tfPrix= new TextField("", "Prix");
        Container cnt=new Container(BoxLayout.x());
        Label lbCategorie=new Label();
        Label lb=new Label("Vegan :");
        Switch sw=new Switch();
        cnt.addAll(lb,sw);
        Button btnValider = new Button("Ajouter menu");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length()==0)||(tfDescription.getText().length()==0) ||(tfPrix.getText().length()==0))
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        if(sw.isValue())
                            lbCategorie.setText("Vegan");
                        else{
                            lbCategorie.setText("Normal");
                        }
                        Menu m = new Menu(tfTitre.getText(), 
                                tfDescription.getText(), Float.parseFloat(tfPrix.getText()), lbCategorie.getText());
                        if( MenuService.getInstance().addMenu(m))
                        {
                           Dialog.show("Success","Menu ajouté avec succès!",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Prix doit être un nombre", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfTitre,tfDescription,tfPrix);
        addAll(cnt,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
