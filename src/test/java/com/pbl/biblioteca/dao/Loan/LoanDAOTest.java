package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.Loan;
import javafx.util.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoanDAOTest {

    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();
        ConnectionMemory.clearMemory();
        ConnectionFile.clearTestFiles();
    }

    @AfterEach
    void tearDown() {
        ConnectionFile.setDefaultFileUrls();
        ConnectionMemory.clearMemory();
        ConnectionFile.clearTestFiles();
    }

    @Test
    void crud() {
        Loan l1 = new Loan("2222", "pedrom", 7, "mendes");
        Loan l2 = new Loan("2222", "ped", 7, "men");

        DAO.getLoanDAO().create(l1);
        DAO.getLoanDAO().create(l2);

        l1 = DAO.getLoanDAO().getByPK(l1.getId());

        assertNotNull(l1);
        assertEquals(2, DAO.getLoanDAO().getAll().size());

        l1.setUsername("mamao");
        DAO.getLoanDAO().update(l1);

        l1 = DAO.getLoanDAO().getByPK(l1.getId());

        assertEquals("mamao", l1.getUsername());

        DAO.getLoanDAO().deleteByPK(l1.getId());
        l1 = DAO.getLoanDAO().getByPK(l1.getId());

        assertNull(l1);
        assertEquals(1, DAO.getLoanDAO().getAll().size());
        assertEquals(1, DAO.getLoanDAO().getTotalLoans());
    }

    @Test
    void getPopularBooksToday(){
        Loan l1 = new Loan("2222", "pedrom", 7, "mendes");
        Loan l2 = new Loan("2222", "ped", 7, "men");
        Loan l3 = new Loan("3333", "ped1", 7, "men1");
        Loan l4 = new Loan("1111", "ped2", 7, "men2");
        Loan l5 = new Loan("2222", "ped6", 7, "men3");
        Loan l6 = new Loan("3333", "ped6", 7, "men3");

        DAO.getLoanDAO().create(l1);
        DAO.getLoanDAO().create(l2);
        DAO.getLoanDAO().create(l3);
        DAO.getLoanDAO().create(l4);
        DAO.getLoanDAO().create(l5);
        DAO.getLoanDAO().create(l6);

        ArrayList<Pair<String, Integer>> popular = DAO.getLoanDAO().getPopularBooksToday();

        assertEquals(3, popular.get(0).getValue());
        assertEquals("2222", popular.get(0).getKey());
        assertEquals(1, popular.get(2).getValue());
        assertEquals("1111", popular.get(2).getKey());
    }

    @Test
    void getTotalOverdueLoans(){
        Loan l1 = new Loan("2222", "pedrom", 7, "mendes");
        Loan l2 = new Loan("2222", "ped", 7, "men");
        Loan l3 = new Loan("3333", "ped1", 15, "men1");

        DAO.getLoanDAO().create(l1);
        DAO.getLoanDAO().create(l2);
        DAO.getLoanDAO().create(l3);

        Integer overdue = DAO.getLoanDAO().getTotalOverdueLoans(LocalDate.now().plusDays(8));

        assertEquals(2, overdue);
    }

    @Test
    void getAllFromUser(){
        Loan l1 = new Loan("2222", "pedrom", 7, "mendes");
        Loan l2 = new Loan("2222", "ped1", 7, "men");
        Loan l3 = new Loan("3333", "pedrom", 15, "men1");

        DAO.getLoanDAO().create(l1);
        DAO.getLoanDAO().create(l2);
        DAO.getLoanDAO().create(l3);

        ArrayList<Loan> fromUser = DAO.getLoanDAO().getAllFromUser("pedrom");

        assertEquals(2, fromUser.size());
    }
}