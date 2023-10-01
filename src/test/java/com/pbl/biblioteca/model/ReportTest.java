package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.fullException;
import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.readerIsBlockedException;
import com.pbl.biblioteca.exceptionHandler.tooManyReservesException;
import javafx.util.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();
        ConnectionMemory.clearMemory();
        ConnectionFile.clearTestFiles();
    }

    @AfterEach
    void tearDown() {
        ConnectionFile.setDefaultFileUrls();
        ConnectionFile.clearTestFiles();
        ConnectionMemory.clearMemory();
    }

    @Test
    void loanLog(){
        Loan l1 = new Loan("2222", "pedrom", 7, "mendes");
        Loan l2 = new Loan("2222", "pedrom", 7, "mendes");

        DAO.getLoanDAO().create(l1);
        DAO.getLoanDAO().create(l2);
        DAO.getReportDAO().logNewLoan(l1);
        DAO.getReportDAO().logNewLoan(l2);

        assertEquals(2, Report.getTotalLoansAllTime());

        DAO.getLoanDAO().deleteByPK(l2.getId());

        assertEquals(2, Report.getTotalLoansAllTime());
    }

    @Test
    void bookLog(){
        Book b1 = new Book("A viagem de coisinho", "Amarelo", "Vermelho",
                2002, "Mistério", "11111", 2);
        Book b2 = new Book("A viagem de coisão", "Preto", "Azul",
                2002, "Mistério", "22222", 2);

        DAO.getBookDAO().create(b1);
        DAO.getBookDAO().create(b2);
        DAO.getReportDAO().logNewBook(b1);
        DAO.getReportDAO().logNewBook(b2);

        assertEquals(2, Report.getAllBookHistory().size());

        DAO.getBookDAO().deleteByPK(b2.getIsbn());

        assertEquals(2, Report.getAllBookHistory().size());
    }

    @Test
    void reserveLog(){
        BookReserve r1 = new BookReserve("pedrom", "2222");
        BookReserve r2 = new BookReserve("pedped", "2222");

        DAO.getBookReserveDAO().create(r1);
        DAO.getBookReserveDAO().create(r2);
        DAO.getReportDAO().logNewReserve(r1);
        DAO.getReportDAO().logNewReserve(r2);

        assertEquals(2, Report.getTotalReservesAllTime());

        DAO.getBookReserveDAO().deleteByPK(r2.getId());

        assertEquals(2, Report.getTotalReservesAllTime());
    }

    @Test
    void userLog(){
        Reader r1 = new Reader("pedromendes",
                "12345", "rua rua", "5259", "Joao");
        Admin a1 = new Admin("pedromendes",
                "12345", "rua rua", "5259", "Joao");
        Librarian l1 = new Librarian("pedromendes",
                "12345", "rua rua", "5259", "Joao");

        DAO.getReaderDAO().create(r1);
        DAO.getAdminDAO().create(a1);
        DAO.getLibrarianDAO().create(l1);

        DAO.getReportDAO().logNewUser(r1);
        DAO.getReportDAO().logNewUser(a1);
        DAO.getReportDAO().logNewUser(l1);

        assertEquals(3, Report.getAllUserHistory().size());

        DAO.getAdminDAO().deleteByPK(a1.getUsername());
        DAO.getReaderDAO().deleteByPK(r1.getUsername());

        assertEquals(3, Report.getAllUserHistory().size());
    }

    @Test
    void getReaderLoanHistory(){
        Loan l1 = new Loan("2222", "pedrom", 7, "mendes");
        Loan l2 = new Loan("3333", "pedrom", 7, "mendes");
        Loan l3 = new Loan("2222", "peped", 7, "mendes");

        DAO.getLoanDAO().create(l1);
        DAO.getLoanDAO().create(l2);
        DAO.getLoanDAO().create(l3);
        DAO.getReportDAO().logNewLoan(l1);
        DAO.getReportDAO().logNewLoan(l2);
        DAO.getReportDAO().logNewLoan(l3);

        assertEquals("2222", Report.getPopularBooksToday().get(0).getKey());
        assertEquals(2, Report.getUserActiveLoans("pedrom").size());
        assertEquals(3, Report.getTotalActiveLoans());

        assertEquals(3, Report.getTotalOverdueLoans(LocalDate.now().plusDays(8)));

        DAO.getLoanDAO().deleteByPK(l2.getId());

        assertEquals(1, Report.getUserActiveLoans("pedrom").size());
        assertEquals(3, Report.getAllLoanHistory().size());
        assertEquals(2, Report.getReaderLoanHistory("pedrom").size());
    }

    @Test
    void getReaderReserveHistory(){
        BookReserve r1 = new BookReserve("pedrom", "2222");
        BookReserve r2 = new BookReserve("peped", "2222");
        BookReserve r3 = new BookReserve("pedrom", "2222");

        DAO.getBookReserveDAO().create(r1);
        DAO.getBookReserveDAO().create(r2);
        DAO.getBookReserveDAO().create(r3);
        DAO.getReportDAO().logNewReserve(r1);
        DAO.getReportDAO().logNewReserve(r2);
        DAO.getReportDAO().logNewReserve(r3);

        DAO.getBookReserveDAO().deleteByPK(r1.getId());

        assertEquals(2, Report.getTotalActiveReserves());
        assertEquals(3, Report.getAllReserveHistory().size());
        assertEquals(2, Report.getReaderReserveHistory("pedrom").size());
    }

    @Test
    void getPopularBooksAllTime(){
        Loan l1 = new Loan("2222", "pedrom", 7, "mendes");
        Loan l2 = new Loan("2222", "ped", 7, "men");
        Loan l3 = new Loan("3333", "ped1", 7, "men1");
        Loan l4 = new Loan("1111", "ped2", 7, "men2");
        Loan l5 = new Loan("2222", "ped6", 7, "men3");

        DAO.getLoanDAO().create(l1);
        DAO.getLoanDAO().create(l2);
        DAO.getLoanDAO().create(l3);
        DAO.getLoanDAO().create(l4);
        DAO.getLoanDAO().create(l5);

        DAO.getReportDAO().logNewLoan(l1);
        DAO.getReportDAO().logNewLoan(l2);
        DAO.getReportDAO().logNewLoan(l3);
        DAO.getReportDAO().logNewLoan(l4);
        DAO.getReportDAO().logNewLoan(l5);

        DAO.getLoanDAO().deleteByPK(l1.getId());
        DAO.getLoanDAO().deleteByPK(l2.getId());
        DAO.getLoanDAO().deleteByPK(l3.getId());
        DAO.getLoanDAO().deleteByPK(l4.getId());
        DAO.getLoanDAO().deleteByPK(l5.getId());

        ArrayList<Pair<String, Integer>> popular = Report.getPopularBooksAllTime();
        assertEquals(3, popular.get(0).getValue());
        assertEquals("2222", popular.get(0).getKey());
        assertEquals(1, popular.get(2).getValue());
        assertEquals("1111", popular.get(2).getKey());
    }
}