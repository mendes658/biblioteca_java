package com.pbl.biblioteca.model;

public class BookCopy extends Book{
    private final Integer id;
    private Boolean borrowed;

    public BookCopy(String title, String author, String publisher,
                    Integer year, String category, String isbn, Integer id) {

        super(title, author, publisher, year, category, isbn);
        this.id = id;
        this.borrowed = false;
    }

    public Integer getId(){
        return this.id;
    }

    public Boolean isBorrowed(){
        return this.borrowed;
    }

    public void borrow(){
        this.borrowed = true;
    }

    public void retrieve(){
        this.borrowed = false;
    }
}
