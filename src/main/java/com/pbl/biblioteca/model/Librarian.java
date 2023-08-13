package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.Loan.LoanDAOImpl;

import java.time.LocalDate;
import java.util.ArrayList;

public class Librarian extends Operator{

    private final ArrayList<String> libLoanIds = new ArrayList<>();

    public Librarian(String newUsername, String newPassword){
        super(newUsername, newPassword);

    }

    public Loan createBookLoan(Book book, User user, Integer days){
        if (!user.isBlocked()){
            LocalDate now = LocalDate.now();
            Loan newLoan = new Loan(book.getIsbn(), user.getNickname(), now, days);
            this.libLoanIds.add(newLoan.getId());
            for (String loanId : this.libLoanIds) {
                System.out.println("ID --->> " + loanId);
            }

            user.updateLoanIds(newLoan.getId());

            LoanDAOImpl.saveLoan(newLoan);

            return newLoan;
        }
        return null;
    }

    public ArrayList<String> getLoanIds(){
        System.out.println(this.libLoanIds.toString());
        return libLoanIds;
    }
}
