package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.BookCopy.BookCopyFileImpl;
import com.pbl.biblioteca.dao.Loan.LoanFileImpl;
import com.pbl.biblioteca.exceptionHandler.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Librarian extends Operator{


    public Librarian(String newUsername, String newPassword){
        super(newUsername, newPassword);

    }

    public Loan createBookLoan(Book book, Reader reader, Integer days) throws
            UserIsBlockedException, CopyNotFoundException {

        if (reader.getBlocked()){
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
            throw new CopyNotFoundException("All copies are currently borrowed.");
        }

        LocalDate today = LocalDate.now();

        LoanFileImpl loanDAO = new LoanFileImpl();
        BookCopyFileImpl bookCopyDAO = new BookCopyFileImpl();

        Loan newLoan = new Loan(freeCopy.getId(), reader.getUsername(), today, days, this.getUsername());
        freeCopy.borrow(newLoan.getId());
        bookCopyDAO.update(freeCopy);

        loanDAO.create(newLoan);

        return newLoan;
    }

    public void deleteBookLoan(String loanId){
        LoanFileImpl loanDAO = new LoanFileImpl();
        BookCopyFileImpl bookCopyDAO = new BookCopyFileImpl();

        Loan toDelete = loanDAO.getByPK(loanId);

        BookCopy bookCopy = bookCopyDAO.getByPK(toDelete.getBookId());
        bookCopy.retrieve();

        loanDAO.deleteByPK(loanId);
        bookCopyDAO.update(bookCopy);
    }
}
