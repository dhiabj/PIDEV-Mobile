/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;



/**
 *
 * @author zacha
 */
public class Commande {
    private int id;
    private String etat;
    private Date date;
    private float total;
    private int user_id;

    public Commande(int id, String etat, Date date, float total, int user_id) {
        this.id = id;
        this.etat = etat;
        this.date = date;
        this.total = total;
        this.user_id = user_id;
    }

    public Commande(String etat, Date date, float total, int user_id) {
        this.etat = etat;
        this.date = date;
        this.total = total;
        this.user_id = user_id;
    }

    public Commande(float total) {
        this.total = total;
    }

    public Commande() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", etat=" + etat + ", date=" + date + ", total=" + total + ", user_id=" + user_id + '}';
    }
    
    
    
}
