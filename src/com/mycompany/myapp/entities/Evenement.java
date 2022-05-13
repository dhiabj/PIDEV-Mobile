/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author USER
 */
public class Evenement {
    private int id;
    private String nom,categorie,description,image;  
    private String date;
    private int nbr_personnes;

    public Evenement() {
    }

    public Evenement(int id, String nom, String categorie, String description, String image, String date, int nbr_personnes) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
        this.date = date;
        this.nbr_personnes = nbr_personnes;
    }

    public Evenement(String nom, String categorie, String description, String image, String date, int nbr_personnes) {
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
        this.date = date;
        this.nbr_personnes = nbr_personnes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbr_personnes() {
        return nbr_personnes;
    }

    public void setNbr_personnes(int nbr_personnes) {
        this.nbr_personnes = nbr_personnes;
    }
    
    
    
}
