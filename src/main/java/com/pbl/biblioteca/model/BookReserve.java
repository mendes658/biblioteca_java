package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.BookReserve.BookReserveDAOImpl;

import java.io.Serializable;

public class BookReserve implements Serializable {
    private final String username;
    private final String bookIsbn;
    private final String id;

    public BookReserve(String user, String isbn){
        this.username = user;
        this.bookIsbn = isbn;

        BookReserveDAOImpl bookReserveDAO = new BookReserveDAOImpl();
        this.id = bookReserveDAO.generateId();

    }

    public String getUsername() {
        return this.username;
    }

    public String getBookIsbn() {
        return this.bookIsbn;
    }

    public String getId(){
        return this.id;
    }

}

