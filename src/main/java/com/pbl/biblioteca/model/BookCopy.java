package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.Book.BookDAO;
import com.pbl.biblioteca.dao.Book.BookDAOImpl;
import com.pbl.biblioteca.dao.BookCopy.BookCopyDAO;
import com.pbl.biblioteca.dao.BookCopy.BookCopyDAOImpl;

import java.io.Serial;
import java.io.Serializable;

public class BookCopy extends Book implements Serializable {

    private Boolean borrowed;

    public BookCopy(String title, String author, String publisher,
                    Integer year, String category, String isbn) {

        super(title, author, publisher, year, category, isbn);

        this.borrowed = false;
    }

    public Boolean isBorrowed(){
        return this.borrowed;
    }

    public void borrow(String loanID){
        this.borrowed = true;
    }

    public void retrieve(){
        this.borrowed = false;
    }

}
