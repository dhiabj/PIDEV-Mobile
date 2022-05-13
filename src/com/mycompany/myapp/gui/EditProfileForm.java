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
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.UserService;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author bhk
 */
public class EditProfileForm extends Form {

    Form current;

    public EditProfileForm(Resources res, User u) {
        System.out.println(UserService.userconnected.getPassword());
        System.out.println(UserService.userconnected.getNum_Tel());
        
        int id = u.getId();
        setTitle("Modifier Profil");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField(u.getNom(), "nom");
        TextField tfprenom = new TextField(u.getPrenom(), "Prenom");
        TextField tfEmail = new TextField(u.getEmail(), "Email");
        TextField tfPassword = new TextField(u.getPassword(), "password", 20, TextField.PASSWORD);
        TextField tfAdr = new TextField(u.getAdresse(), "Adresse");
        TextField tfNumTel = new TextField(u.getNum_Tel(), "Numero Telephone");
        Picker date = new Picker();

        Label LabelRole = new Label("ROLE_USER");
        Label LabelEtat = new Label("Verified");
        Container cnt = new Container(BoxLayout.x());

        Button btnValider = new Button("Modifier Profile");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length() == 0) || (tfprenom.getText().length() == 0) || (tfEmail.getText().length() == 0) || (tfPassword.getText().length() == 0) || (tfAdr.getText().length() == 0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                } else {
                    try {
                        User u = new User(tfEmail.getText(), tfPassword.getText(), LabelRole.getText(), tfnom.getText(), tfprenom.getText(), date.getText(), tfNumTel.getText(), tfAdr.getText(), LabelEtat.getText());
                        if (UserService.getInstance().updateUser(u)) {
                            Dialog.show("Success", "Modifications avec succès!", new Command("OK"));
                            new EditProfileForm(res, u).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "aaa", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfnom, tfprenom, tfEmail, tfPassword, tfNumTel, tfAdr, date);
        addAll(cnt, btnValider);

    }

}
