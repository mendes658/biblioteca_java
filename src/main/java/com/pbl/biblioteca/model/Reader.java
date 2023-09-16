package com.pbl.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Reader extends User implements Serializable {

    private Boolean blocked;
    private LocalDate dateEndBlock;

    public Reader(String username, String name,
                  String address, String telephone, String password){

        super(username, password, address, telephone, name);
        this.blocked = false;
        this.dateEndBlock = null;

    }


    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public void setDateEndBlock(LocalDate dateEndBlock) {
        this.dateEndBlock = dateEndBlock;
    }

    public Boolean getBlocked(){
        return blocked;
    }

    public LocalDate getDateEndBlock(){
        return dateEndBlock;
    }

    /*
    public void blockUser(LocalDate dataFim){
        this.blocked = true;
        this.dateEndBlock = dataFim;
    }

    public void unblockUser(){
        this.blocked = false;
        this.dateEndBlock = null;
    } */

}
