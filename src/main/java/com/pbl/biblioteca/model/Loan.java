package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.dao.Loan.LoanFileImpl;
import com.pbl.biblioteca.exceptionHandler.alreadyRenewedException;
import com.pbl.biblioteca.exceptionHandler.tooManyReservesException;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private final String id;
    private final String bookIsbn;
    private String username;
    private final LocalDate initialDate;
    private LocalDate finalDate;
    private final String librarianUsername;
    private boolean alreadyRenew;

    public Loan(String bookIsbn, String userUsername, Integer loanDays,
                String librarianUsername){
        LoanFileImpl loanDAO = new LoanFileImpl();
        this.id = loanDAO.generateId();
        this.bookIsbn = bookIsbn;
        this.username = userUsername;
        this.initialDate = LocalDate.now();
        this.finalDate = LocalDate.now().plusDays(loanDays);
        this.librarianUsername = librarianUsername;
        this.alreadyRenew = false;
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

    public void renew() throws alreadyRenewedException, tooManyReservesException {
        if (this.alreadyRenew){
            throw new alreadyRenewedException("This loan has been renewed before");
        }

        if (! DAO.getBookReserveDAO().getReservesFromBook(this.bookIsbn).isEmpty()){
            throw new tooManyReservesException("There are reserves to current book, can't renew");
        }

        this.alreadyRenew = true;
        if (this.finalDate != null){
            this.finalDate = this.finalDate.plusDays(7);
        }

        DAO.getLoanDAO().update(this);
    }


}
