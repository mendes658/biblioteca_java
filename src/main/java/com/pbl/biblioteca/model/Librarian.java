package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.LoanDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Librarian extends Operator{

    private final ArrayList<String> libLoanIds = new ArrayList<>();

    public Librarian(String newUsername, String newPassword){
        super(newUsername, newPassword);

    }

    public Loan setBookLoan(String bookISBN, User user, Integer days){
        if (!user.isBlocked()){
            LocalDate now = LocalDate.now();
            Loan newLoan = new Loan(bookISBN, user.getNickname(), now, days);
            this.libLoanIds.add(newLoan.getId());
            for (String loanId : this.libLoanIds) {
                System.out.println("ID --->> " + loanId);
            }

            user.updateLoanIds(newLoan.getId());

            LoanDAO.saveLoan(newLoan);

            return newLoan;
        }
        return null;
    }

    public ArrayList<String> getLoanIds(){
        System.out.println(this.libLoanIds.toString());
        return libLoanIds;
    }
}
