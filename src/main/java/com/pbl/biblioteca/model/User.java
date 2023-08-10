package com.pbl.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class User implements Serializable {
    private static Integer currentId = 0;

    public User(){
        currentId++;
        id = currentId.toString();
        blocked = false;
    }

    private String nickname; // primary key
    private final ArrayList<Integer> loanHashmap = new ArrayList<>();
    private String name;
    private String adress;
    private String telephone;
    private final String id;
    private String password;
    private Boolean blocked;
    private LocalDate dateEndBlock = null;


    // Getters
    public Boolean getBlocked(){
        return blocked;
    }

    public String getNickname() {
        return nickname;
    }

    public ArrayList<Integer> getLoanHashmap() {
        return loanHashmap;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
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
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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
