package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.Loan.LoanDAOImpl;
import com.pbl.biblioteca.exceptionHandler.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Librarian extends Operator{

    private final ArrayList<String> loanIds = new ArrayList<>();

    public Librarian(String newUsername, String newPassword){
        super(newUsername, newPassword);

    }

    public Loan createBookLoan(Book book, User user, Integer days) throws
            UserIsBlockedException, CopyNotFoundException {

        if (user.isBlocked()){
            throw new UserIsBlockedException("User is blocked.");
        }

        ArrayList<BookCopy> allBookCopies = book.getAllCopies();
        boolean found = false;
        BookCopy freeCopy = null;

        for (BookCopy copy : allBookCopies){
            if (!copy.isBorrowed()){
                freeCopy = copy;
                found = true;
                break;
            }
        }

        if (!found){
            throw new CopyNotFoundException("All copies are borrowed.");
        }

        LocalDate today = LocalDate.now();

        LoanDAOImpl loanDAO = new LoanDAOImpl();
        Loan newLoan = new Loan(freeCopy.getId(), user.getUsername(), today, days, this.getUsername());
        freeCopy.borrow(newLoan.getId());
        loanIds.add(newLoan.getId());
        loanDAO.create(newLoan);

        return newLoan;
    }

    public ArrayList<String> getLoanIds(){
        System.out.println(this.loanIds.toString());
        return loanIds;
    }
}
