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
public class Reservation {
    private int id;
    private String nom;
    private int evenement_id;
    private int nbr_personnes;
    private int user_id;

    public Reservation() {
    }

    public Reservation(int id, String nom, int evenement_id, int nbr_personnes, int user_id) {
        this.id = id;
        this.nom = nom;
        this.evenement_id = evenement_id;
        this.nbr_personnes = nbr_personnes;
        this.user_id = user_id;
    }

    public Reservation(String nom, int evenement_id, int nbr_personnes, int user_id) {
        this.nom = nom;
        this.evenement_id = evenement_id;
        this.nbr_personnes = nbr_personnes;
        this.user_id = user_id;
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

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public int getNbr_personnes() {
        return nbr_personnes;
    }

    public void setNbr_personnes(int nbr_personnes) {
        this.nbr_personnes = nbr_personnes;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
    
    
}
