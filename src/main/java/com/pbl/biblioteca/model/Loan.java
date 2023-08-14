package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.Loan.LoanDAOImpl;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {

    public Loan(String bookISBN, String username, LocalDate date, Integer loanDays){
        LoanDAOImpl loanDAO = new LoanDAOImpl();
        this.id = loanDAO.generateId();
        this.bookISBN = bookISBN;
        this.username = username;
        this.initialDate = date;
        this.finalDate = date.plusDays(loanDays);
    }

    private final String id;
    private final String bookISBN;
    private String username;
    private final LocalDate initialDate;
    private final LocalDate finalDate;


    // Getters
    public String getId() {
        return id;
    }

    public String getbookISBN() {
        return bookISBN;
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

    public void setUsername(String username){
        this.username = username;
    }



}
