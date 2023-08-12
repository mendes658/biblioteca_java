package com.pbl.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class User implements Serializable {

    private static Integer currentId = 0;
    private String nickname; // primary key
    private final ArrayList<String> loanIds = new ArrayList<>();
    private String name;
    private String adress;
    private String telephone;
    private final String id;
    private String password;
    private Boolean blocked;
    private LocalDate dateEndBlock;

    public User(String newNickname, String newName,
                String newAdress, String newTelephone, String newPassword){
        currentId++;
        id = currentId.toString();
        blocked = false;
        dateEndBlock = null;

        this.nickname = newNickname;
        this.name = newName;
        this.adress = newAdress;
        this.telephone = newTelephone;
        this.password = newPassword;
    }


    // Getters
    public Boolean isBlocked(){
        return blocked;
    }

    public String getNickname() {
        return nickname;
    }

    public ArrayList<String> getLoanIds() {
        return loanIds;
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

    public void updateLoanIds(String newLoanId){
        this.loanIds.add(newLoanId);
    }

    public boolean deleteLoanId(String loanId){
        for (int i = 0; i< loanIds.size(); i++){
            if (loanIds.get(i).equals(loanId)){
                loanIds.remove(i);
                return true;
            }
        }
        return false;
    }

}
