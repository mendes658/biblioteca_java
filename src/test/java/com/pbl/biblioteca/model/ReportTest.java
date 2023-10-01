package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.fullException;
import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.readerIsBlockedException;
import com.pbl.biblioteca.exceptionHandler.tooManyReservesException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void getGeneralStatistics(){
        Librarian l1 = new Librarian("pedromendes",
                "12345", "rua rua", "5259", "Joao");
        Book b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111", 3);
        Book b2 = new Book("OPAOPAOPA", "Amarelo", "Vermelho",
                2002, "Mistério", "4565445", 3);
        Reader r1 = new Reader("r1",
                "12345", "rua rua", "5259", "pedrin");
        Reader r2 = new Reader("r2",
                "12345", "rua rua", "5259", "ped");
        Reader r3 = new Reader("r3",
                "12345", "rua rua", "5259", "pedrooo");
        Reader r4 = new Reader("r4",
                "12345", "rua rua", "5259", "pedropedro");

        DAO.getLibrarianDAO().create(l1);
        DAO.getBookDAO().create(b1);
        DAO.getReaderDAO().create(r1);
        DAO.getReaderDAO().create(r2);
        DAO.getReaderDAO().create(r3);
        DAO.getReaderDAO().create(r4);


        try {
            l1.createBookLoan(b1, r1, 7);
            l1.createBookLoan(b1, r2, 7);
            l1.createBookLoan(b2, r2, 7);
        }catch (readerIsBlockedException | notFoundException | fullException |
                tooManyReservesException e){
            e.printStackTrace();
        }

        assertEquals(2, Report.getTotalActiveLoans());
        assertEquals(b1.getIsbn(), Report.getPopularBooksToday().get(0).getKey());
        assertEquals(b2.getIsbn(), Report.getPopularBooksToday().get(1).getKey());
        assertEquals(2, Report.getPopularBooksToday().get(0).getValue());
        assertEquals(1, Report.getPopularBooksToday().get(1).getValue());

    }
}