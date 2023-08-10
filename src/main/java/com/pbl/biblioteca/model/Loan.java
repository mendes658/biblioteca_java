package com.pbl.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private static Integer currentId = 0;

    public Loan(){
        currentId++;
        id = currentId.toString();
    }

    private final String id;
    private Integer bookId;
    private Integer userId;
    private LocalDate initialDate;
    private LocalDate finalDate;


    // Getters
    public String getId() {
        return id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }


    // Setters
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setInitialDate(LocalDate date, Integer loanDays) {
        this.initialDate = date;
        this.finalDate = date.plusDays(loanDays);
    }

}
