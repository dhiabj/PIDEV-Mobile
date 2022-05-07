/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class AdminForm extends Form{
Form current;
    public AdminForm() {
        current=this; //Back 
        setTitle("Admin");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisir une option"));
        Button btnAddTask = new Button("Ajouter menu");
        Button btnListTasks = new Button("Menu Liste");
        
        btnAddTask.addActionListener(e-> new AddMenuForm(current).show());
        btnListTasks.addActionListener(e-> new ListMenusForm(current).show());
        addAll(btnAddTask,btnListTasks);
        
        
    }
    
    
}
