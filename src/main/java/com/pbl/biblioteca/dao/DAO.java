package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.dao.Book.BookDAO;
import com.pbl.biblioteca.dao.Book.BookDAOImpl;
import com.pbl.biblioteca.dao.BookReserve.BookReserveDAO;
import com.pbl.biblioteca.dao.BookReserve.BookReserveDAOImpl;
import com.pbl.biblioteca.dao.Librarian.LibrarianDAO;
import com.pbl.biblioteca.dao.Librarian.LibrarianDAOImpl;
import com.pbl.biblioteca.dao.Loan.LoanDAO;
import com.pbl.biblioteca.dao.Loan.LoanDAOImpl;
import com.pbl.biblioteca.dao.Operator.OperatorDAO;
import com.pbl.biblioteca.dao.Operator.OperatorDAOImpl;
import com.pbl.biblioteca.dao.User.UserDAO;
import com.pbl.biblioteca.dao.User.UserDAOImpl;
import com.pbl.biblioteca.model.BookReserve;

public class DAO {

    private static BookDAO bookDAO;
    private static LoanDAO loanDAO;
    private static OperatorDAO operatorDAO;
    private static UserDAO userDAO;
    private static LibrarianDAO librarianDAO;
    private static BookReserveDAO bookReserve;

    public static BookDAO getBookDAO() {
        if (bookDAO == null){
            bookDAO = new BookDAOImpl();
        }

        return bookDAO;
    }

    public static LoanDAO getLoanDAO() {
        if (loanDAO == null){
            loanDAO = new LoanDAOImpl();
        }

        return loanDAO;
    }

    public static OperatorDAO getOperatorDAO() {
        if (operatorDAO == null){
            operatorDAO = new OperatorDAOImpl();
        }

        return operatorDAO;
    }

    public static UserDAO getUserDAO() {
        if (userDAO == null){
            userDAO = new UserDAOImpl();
        }

        return userDAO;
    }

    public static LibrarianDAO getLibrarianDAO() {
        if (librarianDAO == null){
            librarianDAO = new LibrarianDAOImpl();
        }

        return librarianDAO;
    }

    public static BookReserveDAO getBookReserve() {
        if (bookReserve == null){
            bookReserve = new BookReserveDAOImpl();
        }

        return bookReserve;
    }
}
