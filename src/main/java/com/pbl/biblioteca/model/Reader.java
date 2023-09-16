package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.User.UserDAOImpl;

import java.io.Serializable;
import java.time.LocalDate;

public class Reader implements Serializable {

    private String username; // primary key
    private String name;
    private String address;
    private String telephone;
    private final String id;
    private String password;
    private Boolean blocked;
    private LocalDate dateEndBlock;

    public Reader(String username, String newName,
                  String newAdress, String newTelephone, String newPassword){
        UserDAOImpl userDAO = new UserDAOImpl();
        id = userDAO.generateId();
        blocked = false;
        dateEndBlock = null;

        this.username = username;
        this.name = newName;
        this.address = newAdress;
        this.telephone = newTelephone;
        this.password = newPassword;
    }


    // Getters
    public Boolean isBlocked(){
        return blocked;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateEndBlock(){
        return dateEndBlock;
    }


    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void blockUser(LocalDate dataFim){
        this.blocked = true;
        this.dateEndBlock = dataFim;
    }

    public void unblockUser(){
        this.blocked = false;
        this.dateEndBlock = null;
    }

}
