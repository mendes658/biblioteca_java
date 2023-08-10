package com.pbl.biblioteca.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable {

    private static Integer currentId = 0;

    public Book(){
        currentId++;
        id = currentId;
    }


    private final Integer id; // primary key
    private String title;
    private String author;
    private String publisher;
    private Integer year;
    private String category;
    private String isbn; // unique
    private Integer availableCopies;
    private Integer totalCopies;
    private final ArrayList<Integer> loanIds = new ArrayList<>();


    // Getters
    public Integer getId() {
        return id;
    }

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

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public ArrayList<Integer> getLoanIds() {
        return loanIds;
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

    public void setTotalCopies(Integer total) {
        this.totalCopies = total;
        this.availableCopies = total;
    }


    // Controle de empréstimos
    public void addLoan(Integer loanId){
        this.loanIds.add(loanId);
        this.availableCopies--;
    }

    public void removeLoan(Integer loanId){
        for (int i = 0; i < this.loanIds.size(); i++) {
            if (this.loanIds.get(i).equals(loanId)) {
                this.loanIds.remove(i);
                this.availableCopies++;
                break;
            }
        }
    }

}


