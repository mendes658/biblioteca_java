package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class BookReserve implements Serializable {
    private String username;
    private final String bookIsbn;
    private final String id;
    private LocalDate dateEndReserve;
    private final int epochDateSetReserve;

    public BookReserve(String user, String isbn){
        this.username = user;
        this.bookIsbn = isbn;
        this.dateEndReserve = null;
        this.epochDateSetReserve = (int) LocalDate.now().toEpochSecond(LocalTime.now(), ZoneOffset.UTC);

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

    public LocalDate getDateEndReserve() {
        return dateEndReserve;
    }

    public void setDateEndReserve(LocalDate dateEndReserve) {
        this.dateEndReserve = dateEndReserve;
    }

    public int epochDateSetReserve(){
        return epochDateSetReserve;
    }
}

