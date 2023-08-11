package com.pbl.biblioteca.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Librarian extends Operator{
    private String role;
    private final ArrayList<String> loanIds = new ArrayList<>();

    public boolean setBookLoan(String bookISBN, User user, Integer days){
        if (!user.isBlocked()){
            LocalDate now = LocalDate.now();
            Loan newLoan = new Loan(bookISBN, user.getNickname(), now, days);
            loanIds.add(newLoan.getId());
            user.updateLoanIds(newLoan.getId());

            return true;
        }
        return false;
    }
}
