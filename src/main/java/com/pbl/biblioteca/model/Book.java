package com.pbl.biblioteca.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Book implements Serializable {

    private String title;
    private String author;
    private String publisher;
    private Integer year;
    private String category;
    private String isbn; // unique
    private final ArrayList<BookCopy> allCopies = new ArrayList<>();


    public Book(String title, String author, String publisher,
                Integer year, String category, String isbn) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.category = category;
        this.isbn = isbn;


    }


    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }


    public String getPublisher() {
        return publisher;
    }

    public Integer getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public String getIsbn() {
        return isbn;
    }


    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void addCopies(Integer total) {
        int lastIndex = allCopies.size();

        for (int i = lastIndex; i < total; i++){
            allCopies.add(new BookCopy(this.title, this.author, this.publisher, this.year, this.category,
                    this.isbn, i));

        }
    }

    public ArrayList<BookCopy> getAllCopies(){
        return this.allCopies;
    }


}


