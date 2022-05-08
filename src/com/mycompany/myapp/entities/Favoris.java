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
public class Favoris {
    private int id;
    private int user_id;
    private int menu_id;

    public Favoris(int id, int user_id, int menu_id) {
        this.id = id;
        this.user_id = user_id;
        this.menu_id = menu_id;
    }

    public Favoris(int menu_id, int user_id) {
        this.menu_id = menu_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    @Override
    public String toString() {
        return "Favoris{" + "id=" + id + ", user_id=" + user_id + ", menu_id=" + menu_id + '}';
    }
    
    
    
}
