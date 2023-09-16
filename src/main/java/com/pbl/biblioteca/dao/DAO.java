package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.dao.Book.BookDAO;
import com.pbl.biblioteca.dao.Book.BookFileImpl;
import com.pbl.biblioteca.dao.BookReserve.BookReserveDAO;
import com.pbl.biblioteca.dao.BookReserve.BookReserveFileImpl;
import com.pbl.biblioteca.dao.Librarian.LibrarianDAO;
import com.pbl.biblioteca.dao.Librarian.LibrarianFileImpl;
import com.pbl.biblioteca.dao.Loan.LoanDAO;
import com.pbl.biblioteca.dao.Loan.LoanFileImpl;
import com.pbl.biblioteca.dao.Operator.OperatorDAO;
import com.pbl.biblioteca.dao.Operator.OperatorFileImpl;
import com.pbl.biblioteca.dao.User.UserDAO;
import com.pbl.biblioteca.dao.User.UserFileImpl;

public class DAO {

    private static BookDAO bookDAO;
    private static LoanDAO loanDAO;
    private static OperatorDAO operatorDAO;
    private static UserDAO userDAO;
    private static LibrarianDAO librarianDAO;
    private static BookReserveDAO bookReserve;

    public static BookDAO getBookDAO() {
        if (bookDAO == null){
            bookDAO = new BookFileImpl();
        }

        return bookDAO;
    }

    public static LoanDAO getLoanDAO() {
        if (loanDAO == null){
            loanDAO = new LoanFileImpl();
        }

        return loanDAO;
    }

    public static OperatorDAO getOperatorDAO() {
        if (operatorDAO == null){
            operatorDAO = new OperatorFileImpl();
        }

        return operatorDAO;
    }

    public static UserDAO getUserDAO() {
        if (userDAO == null){
            userDAO = new UserFileImpl();
        }

        return userDAO;
    }

    public static LibrarianDAO getLibrarianDAO() {
        if ( librarianDAO == null){
            librarianDAO = new LibrarianFileImpl();
        }

        return librarianDAO;
    }

    public static BookReserveDAO getBookReserve() {
        if (bookReserve == null){
            bookReserve = new BookReserveFileImpl();
        }

        return bookReserve;
    }
}
