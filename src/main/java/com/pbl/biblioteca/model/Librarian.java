package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;

import java.time.LocalDate;

public class Librarian extends Operator{


    public Librarian(String username, String password, String address, String telephone,
                     String name){
        super(username, password, address, telephone, name, "librarian");

    }

    public Loan createBookLoan(Book book, Reader reader, Integer days) throws
            readerIsBlockedException, notFoundException {

        if (reader.getBlocked()) {
            throw new readerIsBlockedException("Reader is blocked.");
        }

        book.borrowCopy();
        LocalDate today = LocalDate.now();
        Loan newLoan = new Loan(book.getIsbn(), reader.getUsername(), today, days, this.getUsername());
        DAO.getLoanDAO().create(newLoan);

        return newLoan;
    }

    public void deleteBookLoan(String loanId){
        Loan toDelete = DAO.getLoanDAO().getByPK(loanId);
        Book book = DAO.getBookDAO().getByPK(toDelete.getBookIsbn());

        book.retrieveCopy();

        DAO.getLoanDAO().deleteByPK(loanId);
        DAO.getBookDAO().update(book);
    }
}
