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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.ReclamationUser;
import com.mycompany.myapp.services.ReclamationUserService;
import com.mycompany.myapp.services.UserService;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author bhk
 */
public class AddRecForm extends Form {

    Form current;
    private String image;

    public AddRecForm( Resources res) {
        setTitle("Envoyer une reclamation");
        setLayout(BoxLayout.y());

        TextField tfTitre = new TextField("", "Titre");
        TextArea tfTexte = new TextArea("Texte");
        Container cnt = new Container(BoxLayout.x());
        Button btnValider = new Button("Ajouter Reclamation");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length() == 0) || (tfTexte.getText().length() == 0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                } else {
                    try {
                        ReclamationUser rec = new ReclamationUser(tfTitre.getText(), tfTexte.getText());
                        if (ReclamationUserService.getInstance().addRec(rec,UserService.userconnected.getId())) {
                            Dialog.show("Success", "Reclamation ajouté avec succès!", new Command("OK"));
                            new ReclamationForm(res).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "error", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfTitre, tfTexte);
        addAll(cnt, btnValider);
       

    }

}
