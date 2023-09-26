package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.dao.Admin.AdminDAO;
import com.pbl.biblioteca.dao.Admin.AdminFileImpl;
import com.pbl.biblioteca.dao.Admin.AdminMemoryImpl;
import com.pbl.biblioteca.dao.Book.BookDAO;
import com.pbl.biblioteca.dao.Book.BookFileImpl;
import com.pbl.biblioteca.dao.BookReserve.BookReserveDAO;
import com.pbl.biblioteca.dao.BookReserve.BookReserveFileImpl;
import com.pbl.biblioteca.dao.BookReserve.BookReserveMemoryImpl;
import com.pbl.biblioteca.dao.Librarian.LibrarianDAO;
import com.pbl.biblioteca.dao.Librarian.LibrarianFileImpl;
import com.pbl.biblioteca.dao.Librarian.LibrarianMemoryImpl;
import com.pbl.biblioteca.dao.Loan.LoanDAO;
import com.pbl.biblioteca.dao.Loan.LoanFileImpl;
import com.pbl.biblioteca.dao.Loan.LoanMemoryImpl;
import com.pbl.biblioteca.dao.Operator.OperatorDAO;
import com.pbl.biblioteca.dao.Operator.OperatorFileImpl;
import com.pbl.biblioteca.dao.Operator.OperatorMemoryImpl;
import com.pbl.biblioteca.dao.Reader.ReaderDAO;
import com.pbl.biblioteca.dao.Reader.ReaderFileImpl;
import com.pbl.biblioteca.dao.Reader.ReaderMemoryImpl;
import com.pbl.biblioteca.dao.Report.ReportDAO;
import com.pbl.biblioteca.dao.Report.ReportFileImpl;
import com.pbl.biblioteca.dao.Report.ReportMemoryImpl;
import com.pbl.biblioteca.dao.System.SystemDAO;
import com.pbl.biblioteca.dao.System.SystemFileImpl;
import com.pbl.biblioteca.dao.System.SystemMemoryImpl;
import com.pbl.biblioteca.dao.User.UserDAO;
import com.pbl.biblioteca.dao.User.UserFileImpl;
import com.pbl.biblioteca.dao.User.UserMemoryImpl;

public class DAO {

    // 1 - Memória ; 2 - Arquivo
    private static final int TYPE_OF_STORAGE = 1;

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
            if (TYPE_OF_STORAGE == 1){
                reportDAO = new ReportMemoryImpl();
            } else {
                reportDAO = new ReportMemoryImpl();
            }
        }

        return reportDAO;
    }

    public static SystemDAO getSystemDAO() {
        if (systemDAO == null){
            if (TYPE_OF_STORAGE == 1){
                systemDAO = new SystemMemoryImpl();
            } else {
                systemDAO = new SystemFileImpl();
            }
        }

        return systemDAO;
    }

    public static AdminDAO getAdminDAO() {
        if (adminDAO == null){
            if (TYPE_OF_STORAGE == 1){
                adminDAO = new AdminMemoryImpl();
            } else {
                adminDAO = new AdminFileImpl();
            }
        }

        return adminDAO;
    }

    public static ReaderDAO getReaderDAO() {
        if (readerDAO == null){
            if (TYPE_OF_STORAGE == 1){
                readerDAO = new ReaderMemoryImpl();
            } else {
                readerDAO = new ReaderFileImpl();
            }
        }

        return readerDAO;
    }

    public static LoanDAO getLoanDAO() {
        if (loanDAO == null){
            if (TYPE_OF_STORAGE == 1){
                loanDAO = new LoanMemoryImpl();
            } else {
                loanDAO = new LoanFileImpl();
            }
        }

        return loanDAO;
    }

    public static OperatorDAO getOperatorDAO() {
        if (operatorDAO == null){
            if (TYPE_OF_STORAGE == 1){
                operatorDAO = new OperatorMemoryImpl();
            } else {
                operatorDAO = new OperatorFileImpl();
            }

        }

        return operatorDAO;
    }

    public static UserDAO getUserDAO() {
        if (userDAO == null){
            if (TYPE_OF_STORAGE == 1){
                userDAO = new UserMemoryImpl();
            } else {
                userDAO = new UserFileImpl();
            }
        }

        return userDAO;
    }

    public static LibrarianDAO getLibrarianDAO() {
        if ( librarianDAO == null){
            if (TYPE_OF_STORAGE == 1){
                librarianDAO = new LibrarianMemoryImpl();
            } else {
                librarianDAO = new LibrarianFileImpl();
            }

        }

        return librarianDAO;
    }

    public static BookReserveDAO getBookReserveDAO() {
        if (bookReserveDAO == null){
            if (TYPE_OF_STORAGE == 1){
                bookReserveDAO = new BookReserveMemoryImpl();
            } else {
                bookReserveDAO = new BookReserveFileImpl();
            }
        }

        return bookReserveDAO;
    }
}
