package com.pbl.biblioteca.dao.Librarian;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Librarian;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianDAOImplTest {

    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();
    }

    @AfterEach
    void tearDown() {
        ConnectionFile.setDefaultFileUrls();
    }

    @Test
    void createAndGetByPK() {
        LibrarianFileImpl librarianDAO = new LibrarianFileImpl();
        Librarian l1 = new Librarian("pedromendes", "12345");
        librarianDAO.create(l1);
        assertEquals(librarianDAO.getByPK("pedromendes").getUsername(), "pedromendes");
    }

    @Test
    void update() {
        LibrarianFileImpl librarianDAO = new LibrarianFileImpl();
        Librarian l1 = new Librarian("pedromendes", "12345");
        librarianDAO.create(l1);
        l1.setUsername("joaquim");
        librarianDAO.update(l1);
        assertEquals(librarianDAO.getByPK("joaquim").getUsername(), "joaquim");
    }

    @Test
    void deleteByPK() {
        LibrarianFileImpl librarianDAO = new LibrarianFileImpl();
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
        LibrarianFileImpl librarianDAO = new LibrarianFileImpl();
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
        LibrarianFileImpl librarianDAO = new LibrarianFileImpl();
        Librarian l1 = new Librarian("pedromendes", "12345");
        Librarian l2 = new Librarian("pedromendes22", "12345");
        librarianDAO.create(l1);
        librarianDAO.create(l2);

        int a = Integer.parseInt(l1.getId());
        int b = Integer.parseInt(l2.getId());
        assertTrue(b > a);
    }
}