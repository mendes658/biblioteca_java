package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;

import java.io.Serializable;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class User implements Serializable {

    private final String username;
    private final String id;
    private final String type;
    private String password;
    private String address;
    private String telephone;
    private String name;

    protected User(String username, String password, String address, String telephone, String name, String type){
        this.username = username;
        this.password = password;
        this.address = address;
        this.telephone = telephone;
        this.name = name;
        this.type = type;

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

    public String getType() {
        return type;
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
