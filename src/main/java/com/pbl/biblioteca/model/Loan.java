package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.Loan.LoanFileImpl;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {

    public Loan(String bookId, String userUsername, LocalDate date, Integer loanDays,
                String librarianUsername){
        LoanFileImpl loanDAO = new LoanFileImpl();
        this.id = loanDAO.generateId();
        this.bookId = bookId;
        this.username = userUsername;
        this.initialDate = date;
        this.finalDate = date.plusDays(loanDays);
        this.librarianUsername = librarianUsername;
    }

    private final String id;
    private final String bookId;
    private String username;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final String librarianUsername;


    // Getters
    public String getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public String getLibrarianUsername() {
        return librarianUsername;
    }

    public void setUsername(String username){
        this.username = username;
    }



}
