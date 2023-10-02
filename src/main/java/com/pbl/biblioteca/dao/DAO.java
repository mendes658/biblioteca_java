package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.dao.Admin.AdminDAO;
import com.pbl.biblioteca.dao.Admin.AdminFileImpl;
import com.pbl.biblioteca.dao.Admin.AdminMemoryImpl;
import com.pbl.biblioteca.dao.Book.BookDAO;
import com.pbl.biblioteca.dao.Book.BookFileImpl;
import com.pbl.biblioteca.dao.Book.BookMemoryImpl;
import com.pbl.biblioteca.dao.BookReserve.BookReserveDAO;
import com.pbl.biblioteca.dao.BookReserve.BookReserveFileImpl;
import com.pbl.biblioteca.dao.BookReserve.BookReserveMemoryImpl;
import com.pbl.biblioteca.dao.Librarian.LibrarianDAO;
import com.pbl.biblioteca.dao.Librarian.LibrarianFileImpl;
import com.pbl.biblioteca.dao.Librarian.LibrarianMemoryImpl;
import com.pbl.biblioteca.dao.Loan.LoanDAO;
import com.pbl.biblioteca.dao.Loan.LoanFileImpl;
import com.pbl.biblioteca.dao.Loan.LoanMemoryImpl;
import com.pbl.biblioteca.dao.Reader.ReaderDAO;
import com.pbl.biblioteca.dao.Reader.ReaderFileImpl;
import com.pbl.biblioteca.dao.Reader.ReaderMemoryImpl;
import com.pbl.biblioteca.dao.Report.ReportDAO;
import com.pbl.biblioteca.dao.Report.ReportFileImpl;
import com.pbl.biblioteca.dao.Report.ReportMemoryImpl;
import com.pbl.biblioteca.dao.User.UserDAO;
import com.pbl.biblioteca.dao.User.UserFileImpl;
import com.pbl.biblioteca.dao.User.UserMemoryImpl;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class DAO {

    /**
     * Escolhe se os dados serão salvos na memória ou em um arquivo
     * 1 = Memória
     * 2 = Arquivo
     */
    private static final int TYPE_OF_STORAGE = 1;

    public static BookDAO bookDAO;
    private static LoanDAO loanDAO;
    private static UserDAO userDAO;
    private static LibrarianDAO librarianDAO;
    private static BookReserveDAO bookReserveDAO;
    private static ReaderDAO readerDAO;
    private static AdminDAO adminDAO;
    private static ReportDAO reportDAO;

    /**
     * Retorna a implementação do bookDAO, de acordo com a TYPE_OF_STORAGE
     * setada como constante nessa classe
     * @return Retorna o DAO correspondente
     */
    public static BookDAO getBookDAO() {
        if (bookDAO == null){
            if (TYPE_OF_STORAGE == 1){
                bookDAO = new BookMemoryImpl();
            } else {
                bookDAO = new BookFileImpl();
            }
        }

        return bookDAO;
    }

    /**
     * Retorna a implementação do reportDAO, de acordo com a TYPE_OF_STORAGE
     * setada como constante nessa classe
     * @return Retorna o DAO correspondente
     */
    public static ReportDAO getReportDAO() {
        if (reportDAO == null){
            if (TYPE_OF_STORAGE == 1){
                reportDAO = new ReportMemoryImpl();
            } else {
                reportDAO = new ReportFileImpl();
            }
        }

        return reportDAO;
    }

    /**
     * Retorna a implementação do adminDAO, de acordo com a TYPE_OF_STORAGE
     * setada como constante nessa classe
     * @return Retorna o DAO correspondente
     */
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

    /**
     * Retorna a implementação do readerDAO, de acordo com a TYPE_OF_STORAGE
     * setada como constante nessa classe
     * @return Retorna o DAO correspondente
     */
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

    /**
     * Retorna a implementação do loanDAO, de acordo com a TYPE_OF_STORAGE
     * setada como constante nessa classe
     * @return Retorna o DAO correspondente
     */
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

    /**
     * Retorna a implementação do userDAO, de acordo com a TYPE_OF_STORAGE
     * setada como constante nessa classe
     * @return Retorna o DAO correspondente
     */
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

    /**
     * Retorna a implementação do librarianDAO, de acordo com a TYPE_OF_STORAGE
     * setada como constante nessa classe
     * @return Retorna o DAO correspondente
     */
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

    /**
     * Retorna a implementação do bookReserveDAO, de acordo com a TYPE_OF_STORAGE
     * setada como constante nessa classe
     * @return Retorna o DAO correspondente
     */
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
