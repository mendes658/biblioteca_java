package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;

import java.io.Serializable;

public class User implements Serializable {

    private final String username;
    private final String id;
    private String password;
    private String address;
    private String telephone;
    private String name;

    public User(String username, String password, String address, String telephone, String name){
        this.username = username;
        this.password = password;
        this.address = address;
        this.telephone = telephone;
        this.name = name;

        this.id = DAO.getUserDAO().generateId();
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
