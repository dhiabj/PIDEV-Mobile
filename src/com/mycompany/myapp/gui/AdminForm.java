/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class AdminForm extends Form{
Form current;
    public AdminForm(Resources res) {
        current=this; //Back 
        setTitle("Admin");
        setLayout(BoxLayout.y());
        Container cnt=new Container(new FlowLayout(CENTER, CENTER));
        ImageViewer logo=new ImageViewer(res.getImage("foru.png"));
        cnt.add(logo);
        
        add(new Label("Choisir une option"));
        Button btnAddTask = new Button("Ajouter menu");
        Button btnListTasks = new Button("Menu Liste");
        
        btnAddTask.addActionListener(e-> new AddMenuForm(current, res).show());
        btnListTasks.addActionListener(e-> new ListMenusForm(current, res).show());
        addAll(cnt,btnAddTask,btnListTasks);
        
        
    }
    
    
}
