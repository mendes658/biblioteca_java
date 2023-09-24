package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.dao.Admin.AdminDAO;
import com.pbl.biblioteca.dao.Admin.AdminFileImpl;
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
import com.pbl.biblioteca.dao.Reader.ReaderDAO;
import com.pbl.biblioteca.dao.Reader.ReaderFileImpl;
import com.pbl.biblioteca.dao.Report.ReportDAO;
import com.pbl.biblioteca.dao.Report.ReportFileImpl;
import com.pbl.biblioteca.dao.System.SystemDAO;
import com.pbl.biblioteca.dao.System.SystemFileImpl;
import com.pbl.biblioteca.dao.User.UserDAO;
import com.pbl.biblioteca.dao.User.UserFileImpl;

public class DAO {

    private static BookDAO bookDAO;
    private static LoanDAO loanDAO;
    private static OperatorDAO operatorDAO;
    private static UserDAO userDAO;
    private static LibrarianDAO librarianDAO;
    private static BookReserveDAO bookReserveDAO;
    private static ReaderDAO readerDAO;
    private static AdminDAO adminDAO;
    private static SystemDAO systemDAO;
    private static ReportDAO reportDAO;

    public static BookDAO getBookDAO() {
        if (bookDAO == null){
            bookDAO = new BookFileImpl();
        }

        return bookDAO;
    }

    public static ReportDAO getReportDAO() {
        if (reportDAO == null){
            reportDAO = new ReportFileImpl();
        }

        return reportDAO;
    }

    public static SystemDAO getSystemDAO() {
        if (systemDAO == null){
            systemDAO = new SystemFileImpl();
        }

        return systemDAO;
    }

    public static AdminDAO getAdminDAO() {
        if (adminDAO == null){
            adminDAO = new AdminFileImpl();
        }

        return adminDAO;
    }

    public static ReaderDAO getReaderDAO() {
        if (readerDAO == null){
            readerDAO = new ReaderFileImpl();
        }

        return readerDAO;
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

    public static BookReserveDAO getBookReserveDAO() {
        if (bookReserveDAO == null){
            bookReserveDAO = new BookReserveFileImpl();
        }

        return bookReserveDAO;
    }
}
