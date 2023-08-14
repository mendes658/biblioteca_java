package com.pbl.biblioteca.dao.Librarian;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.dao.Loan.LoanDAOImpl;
import com.pbl.biblioteca.model.Librarian;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianDAOImplTest {

    @BeforeEach
    void setUp() {
        ConnectionDAO.setTestFileUrls();
    }

    @AfterEach
    void tearDown() {
        ConnectionDAO.setDefaultFileUrls();
    }

    @Test
    void createAndGetByPK() {
        LibrarianDAOImpl librarianDAO = new LibrarianDAOImpl();
        Librarian l1 = new Librarian("pedromendes", "12345");
        librarianDAO.create(l1);
        assertEquals(librarianDAO.getByPK("pedromendes").getUsername(), "pedromendes");
    }

    @Test
    void update() {
        LibrarianDAOImpl librarianDAO = new LibrarianDAOImpl();
        Librarian l1 = new Librarian("pedromendes", "12345");
        librarianDAO.create(l1);
        l1.setUsername("joaquim");
        librarianDAO.update(l1);
        assertEquals(librarianDAO.getByPK("joaquim").getUsername(), "joaquim");
    }

    @Test
    void deleteByPK() {
        LibrarianDAOImpl librarianDAO = new LibrarianDAOImpl();
        Librarian l1 = new Librarian("pedromendes", "12345");
        Librarian l2 = new Librarian("mendes22", "12345");
        librarianDAO.create(l1);
        librarianDAO.create(l2);
        librarianDAO.deleteByPK("pedromendes");
        assertNull(librarianDAO.getByPK("pedromendes"));
        assertEquals(librarianDAO.getByPK("mendes22").getUsername(), "mendes22");
    }

    @Test
    void getAll() {
        LibrarianDAOImpl librarianDAO = new LibrarianDAOImpl();
        Librarian l1 = new Librarian("pedromendes", "12345");
        Librarian l2 = new Librarian("mendes22", "12345");
        librarianDAO.create(l1);
        librarianDAO.create(l2);
        HashMap<String, Librarian> all = librarianDAO.getAll();
        assertEquals(all.get("pedromendes").getUsername(), "pedromendes");
        assertEquals(all.get("mendes22").getUsername(), "mendes22");
    }

    @Test
    void generateId() {
        LibrarianDAOImpl librarianDAO = new LibrarianDAOImpl();
        Librarian l1 = new Librarian("pedromendes", "12345");
        Librarian l2 = new Librarian("pedromendes22", "12345");
        librarianDAO.create(l1);
        librarianDAO.create(l2);

        int a = Integer.parseInt(l1.getId());
        int b = Integer.parseInt(l2.getId());
        assertTrue(b > a);
    }
}