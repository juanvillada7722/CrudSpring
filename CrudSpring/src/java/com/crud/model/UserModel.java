/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.model;

/**
 *
 * @author Juan Villada
 */
public class UserModel {

    private int id;
    private String username;
    private String email;
    private String password;

    public UserModel() {
    }

    public UserModel(int id, String username, String email, String password) {

        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;

    }

    public void setId(int id) {

        this.id = id;

    }

    public int getId() {

        return id;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getUsername() {

        return username;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getEmail() {

        return email;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getPassword() {

        return password;
    }
}
