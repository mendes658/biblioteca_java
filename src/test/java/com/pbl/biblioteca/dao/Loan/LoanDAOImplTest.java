package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.dao.Operator.OperatorDAOImpl;
import com.pbl.biblioteca.model.Loan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
        LoanDAOImpl loanDAO= new LoanDAOImpl();
        Loan l1 = new Loan("12345", "pedromendes33", LocalDate.now(), 7);
        loanDAO.create(l1);
        Loan getL1 = loanDAO.getByPK(l1.getId());

        assertEquals(getL1.getId(), l1.getId());
    }

    @Test
    void getByPK() {
        LoanDAOImpl loanDAO= new LoanDAOImpl();
        Loan l1 = new Loan("12345", "pedromendes33", LocalDate.now(), 7);
        loanDAO.create(l1);
        assertEquals(loanDAO.getByPK(l1.getId()).getUsername(), l1.getUsername());

    }

    @Test
    void update() {
        LoanDAOImpl loanDAO= new LoanDAOImpl();
        Loan l1 = new Loan("12345", "pedromendes33", LocalDate.now(), 7);
        loanDAO.create(l1);
        l1.setUsername("cavalo");
        loanDAO.update(l1);

        Loan newl1 = loanDAO.getByPK(l1.getId());

        assertEquals(newl1.getUsername(), "cavalo");
    }

    @Test
    void deleteByPK() {
        LoanDAOImpl loanDAO = new LoanDAOImpl();
        Loan l1 = new Loan("12345", "pedromendes33", LocalDate.now(), 7);
        loanDAO.create(l1);
        loanDAO.deleteByPK(l1.getId());
        assertNull(loanDAO.getByPK(l1.getId()));

    }

    @Test
    void getAll() {
        LoanDAOImpl loanDAO = new LoanDAOImpl();
        Loan l1 = new Loan("12345", "pedromendes33", LocalDate.now(), 7);
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
}