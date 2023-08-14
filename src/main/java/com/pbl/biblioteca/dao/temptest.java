package com.pbl.biblioteca.dao;
import com.pbl.biblioteca.dao.Loan.LoanDAOImpl;
import com.pbl.biblioteca.model.Loan;

import java.time.LocalDate;
import java.util.HashMap;

public class temptest {
    protected static final String userFileUrl = "users.ser";
    protected static final String loanFileUrl = "loans.ser";
    protected static final String operatorFileUrl = "operators.ser";
    protected static final String bookFileUrl = "books.ser";
    protected static final String librarianFileUrl = "librarians.ser";
    protected static final String booksIsbnsByCategoryUrl = "books_ids_category.ser";

    public static void main(String[] args){
        LoanDAOImpl loanDAO = new LoanDAOImpl();

        Loan test = new Loan("1212", "2222", LocalDate.now(), 7);
        Loan test2 = new Loan("45212", "3332", LocalDate.now(), 7);
        Loan test3 = new Loan("589212", "4442", LocalDate.now(), 7);

        loanDAO.create(test);
        loanDAO.create(test2);
        loanDAO.create(test3);

        HashMap<String, Loan> testHM = ConnectionDAO.getAnySavedHashmap(loanFileUrl);

        loanDAO.create(test);
        loanDAO.create(test2);
        loanDAO.create(test3);

        System.out.println(loanDAO.getByPK("1").getbookISBN());
        System.out.println(loanDAO.getByPK("2").getbookISBN());
        System.out.println(loanDAO.getByPK("3").getbookISBN());

    }
}
