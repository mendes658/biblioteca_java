package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;

import java.io.Serializable;

public class BookReserve implements Serializable {
    private String username;
    private final String bookIsbn;
    private final String id;

    public BookReserve(String user, String isbn){
        this.username = user;
        this.bookIsbn = isbn;

        this.id = DAO.getBookReserveDAO().generateId();

    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getBookIsbn() {
        return this.bookIsbn;
    }

    public String getId(){
        return this.id;
    }

}

