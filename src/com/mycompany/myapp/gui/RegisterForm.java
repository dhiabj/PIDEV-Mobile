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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.MenuService;
import com.mycompany.myapp.services.UserService;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author bhk
 */
public class RegisterForm extends Form {

    Form current;
    private String image;

    public RegisterForm(Resources res) {
        setTitle("Creér une compte");
        setLayout(BoxLayout.y());

        TextField tfNom = new TextField("", "Nom");
        TextField tfPrenom = new TextField("", "Prenom");
        TextField tfEmail = new TextField("", "Email");
        TextField tfPassword = new TextField("", "password", 20, TextField.PASSWORD);
        TextField tfNumTel = new TextField("", "Numero Telephone");
        TextField tfAdr = new TextField("", "Adresse");
        Picker date = new Picker();
        Container cnt = new Container(BoxLayout.x());
        Label LabelRole = new Label("ROLE_USER");
        Label LabelEtat = new Label("Verified");

        Button btnValider = new Button("Register");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfPrenom.getText().length() == 0) || (tfEmail.getText().length() == 0) || (tfPassword.getText().length() == 0) || (tfNumTel.getText().length() == 0) || (tfAdr.getText().length() == 0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                } else {

                    User u = new User(tfEmail.getText(), tfPassword.getText(), LabelRole.getText(), tfNom.getText(), tfPrenom.getText(), date.getText(), tfNumTel.getText(), tfAdr.getText(), LabelEtat.getText());
                    if (UserService.getInstance().addUser(u)) {
                        Dialog.show("Success", "Register avec succès!", new Command("OK"));
                        new MenuForm(res).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfNom, tfPrenom, tfEmail, tfPassword, tfNumTel,tfAdr,date);
        addAll(cnt, btnValider);


    }

}
