package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.Book.BookDAOImpl;
import com.pbl.biblioteca.dao.BookCopy.BookCopyDAOImpl;
import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.dao.Operator.OperatorDAOImpl;
import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.BookCopy;
import com.pbl.biblioteca.model.Loan;
import javafx.util.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LoanDAOImplTest {

    @BeforeEach
    void setUp() {
        ConnectionDAO.setTestFileUrls();
    }

    @AfterEach
    void tearDown() {
        ConnectionDAO.setDefaultFileUrls();
    }

    @Test
    void createAndGet() {
        Book b1;
        BookDAOImpl bookDAO = new BookDAOImpl();
        b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        b1.addCopies(2);

        bookDAO.create(b1);

        LoanDAOImpl loanDAO= new LoanDAOImpl();
        Loan l1 = new Loan(b1.getAllCopies().get(0).getId(), "pedromendes33", LocalDate.now(), 7, "zezinho");
        loanDAO.create(l1);
        Loan getL1 = loanDAO.getByPK(l1.getId());

        assertEquals(getL1.getId(), l1.getId());
    }

    @Test
    void getByPK() {
        Book b1;
        BookDAOImpl bookDAO = new BookDAOImpl();
        b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        b1.addCopies(2);

        bookDAO.create(b1);

        LoanDAOImpl loanDAO= new LoanDAOImpl();
        Loan l1 = new Loan(b1.getAllCopies().get(0).getId(), "pedromendes33", LocalDate.now(), 7, "zezinho");
        loanDAO.create(l1);
        assertEquals(loanDAO.getByPK(l1.getId()).getUsername(), l1.getUsername());

    }

    @Test
    void update() {
        Book b1;
        BookDAOImpl bookDAO = new BookDAOImpl();
        b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        b1.addCopies(2);

        bookDAO.create(b1);

        LoanDAOImpl loanDAO= new LoanDAOImpl();
        Loan l1 = new Loan(b1.getAllCopies().get(0).getId(), "pedromendes33", LocalDate.now(), 7, "zezinho");
        loanDAO.create(l1);
        l1.setUsername("cavalo");
        loanDAO.update(l1);

        Loan newl1 = loanDAO.getByPK(l1.getId());

        assertEquals(newl1.getUsername(), "cavalo");
    }

    @Test
    void deleteByPK() {
        Book b1;
        BookDAOImpl bookDAO = new BookDAOImpl();
        b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        b1.addCopies(2);

        bookDAO.create(b1);

        LoanDAOImpl loanDAO = new LoanDAOImpl();
        Loan l1 = new Loan(b1.getAllCopies().get(0).getId(), "pedromendes33", LocalDate.now(), 7, "zezinho");
        loanDAO.create(l1);
        loanDAO.deleteByPK(l1.getId());
        assertNull(loanDAO.getByPK(l1.getId()));

    }

    @Test
    void getAll() {
        Book b1;
        BookDAOImpl bookDAO = new BookDAOImpl();
        b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        b1.addCopies(2);

        bookDAO.create(b1);

        LoanDAOImpl loanDAO = new LoanDAOImpl();
        Loan l1 = new Loan(b1.getAllCopies().get(0).getId(), "pedromendes33", LocalDate.now(), 7, "zezinho");
        loanDAO.create(l1);
        HashMap<String, Loan> all = loanDAO.getAll();
        assertEquals(all.get(l1.getId()).getUsername(), "pedromendes33");
    }

    @Test
    void generateId() {
        LoanDAOImpl loanDAO = new LoanDAOImpl();
        int a = Integer.parseInt(loanDAO.generateId());
        int b = Integer.parseInt(loanDAO.generateId());
        assertTrue(b > a);
    }

    @Test
    void getPopularBooksAllTime(){
        Book b1, b2, b3;
        BookDAOImpl bookDAO = new BookDAOImpl();
        LoanDAOImpl loanDAO = new LoanDAOImpl();

        b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        b2 = new Book("A batida do maneco", "Preto", "Azul",
                2002, "Mistério", "22222");
        b3 = new Book("A volta dos que não foram", "Preto", "Azul",
                2002, "Mistério", "33333");

        b1.addCopies(3);
        b2.addCopies(3);
        b3.addCopies(3);

        bookDAO.create(b1);
        bookDAO.create(b2);
        bookDAO.create(b3);


        Loan l1 = new Loan(b1.getAllCopies().get(0).getId(), "mendes", LocalDate.now(),
                7, "pedro");

        Loan l2 = new Loan(b1.getAllCopies().get(0).getId(), "mendes", LocalDate.now(),
                7, "pedro");

        Loan l3 = new Loan(b1.getAllCopies().get(0).getId(), "mendes", LocalDate.now(),
                7, "pedro");
        Loan l4 = new Loan(b2.getAllCopies().get(0).getId(), "mendes", LocalDate.now(),
                7, "pedro");
        Loan l5 = new Loan(b2.getAllCopies().get(0).getId(), "mendes", LocalDate.now(),
                7, "pedro");
        Loan l6 = new Loan(b3.getAllCopies().get(0).getId(), "mendes", LocalDate.now(),
                7, "pedro");

        loanDAO.create(l1);
        loanDAO.create(l2);
        loanDAO.create(l3);
        loanDAO.create(l4);
        loanDAO.create(l5);
        loanDAO.create(l6);

        ArrayList<Pair<String, Integer>> popular = loanDAO.getPopularBooksAllTime();
        assertEquals(popular.get(0).getKey(), "11111 Teco teleco teco");
        assertEquals(popular.get(1).getKey(), "22222 A batida do maneco");
        assertEquals(popular.get(2).getKey(), "33333 A volta dos que não foram");
    }
}