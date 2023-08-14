package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.Book.BookDAOImpl;

public class BookCopy extends Book{

    private Boolean borrowed;
    private String loanID;

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
        this.loanID = loanID;
    }

    public void retrieve(){
        this.borrowed = false;
        this.loanID = null;
    }

    public String getLoanID(){
        return this.loanID;
    }

}
