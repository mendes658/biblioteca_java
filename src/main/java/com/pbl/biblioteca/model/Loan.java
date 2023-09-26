package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.Loan.LoanFileImpl;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private final String id;
    private final String bookIsbn;
    private String username;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final String librarianUsername;


    public Loan(String bookIsbn, String userUsername, Integer loanDays,
                String librarianUsername){
        LoanFileImpl loanDAO = new LoanFileImpl();
        this.id = loanDAO.generateId();
        this.bookIsbn = bookIsbn;
        this.username = userUsername;
        this.initialDate = LocalDate.now();
        this.finalDate = LocalDate.now().plusDays(loanDays);
        this.librarianUsername = librarianUsername;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getBookIsbn() {
        return bookIsbn;
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
