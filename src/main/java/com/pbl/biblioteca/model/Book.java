package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.Book.BookDAOImpl;
import com.pbl.biblioteca.dao.BookCopy.BookCopyDAOImpl;

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
    private final String id;
    private final ArrayList<BookCopy> allCopies;

    private Integer totalCopies;
    private Integer availableCopies;


    public Book(String title, String author, String publisher,
                Integer year, String category, String isbn, Integer totalCopies) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.category = category;
        this.isbn = isbn;
        this.allCopies = new ArrayList<>();
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;

        BookDAOImpl bookDAO = new BookDAOImpl();
        this.id = bookDAO.generateId();

    }


    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
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

    /*
    public void addCopies(Integer total) {
        int lastIndex = allCopies.size();
        BookCopyDAOImpl copyDao = new BookCopyDAOImpl();
        BookCopy newCopy;

        for (int i = lastIndex; i < total; i++){
            newCopy = new BookCopy(this.title, this.author, this.publisher, this.year, this.category,
                    this.isbn);
            copyDao.create(newCopy);
            allCopies.add(newCopy);
        }
    } */

    public ArrayList<BookCopy> getAllCopies(){
        return this.allCopies;
    }

    public void addCopies(Integer total){
        this.totalCopies++;
    }


}


