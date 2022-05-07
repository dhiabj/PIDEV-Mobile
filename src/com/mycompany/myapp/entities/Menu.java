/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Dhia
 */
public class Menu {
    private int id;
    private String titre;
    private String description;
    private float prix;
    private String categorie;
    private String image;

    public Menu(int id, String titre, String description, float prix, String categorie, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.image = image;
    }

    public Menu(String titre, String description, float prix, String categorie) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
    }
    
    

    public Menu() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", prix=" + prix + ", categorie=" + categorie + ", image=" + image + '}';
    }
    
    
    
}
