package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.LoanDAO;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private static Integer currentId = 0;

    public Loan(String newBookISBN, String newUserId, LocalDate date, Integer loanDays){
        currentId++;
        id = currentId.toString();

        this.bookISBN = newBookISBN;
        this.userNickname = newUserId;
        this.initialDate = date;
        this.finalDate = date.plusDays(loanDays);
    }

    private final String id;
    private final String bookISBN;
    private final String userNickname;
    private final LocalDate initialDate;
    private final LocalDate finalDate;


    // Getters
    public String getId() {
        return id;
    }

    public String getbookISBN() {
        return bookISBN;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }



}
