package com.pbl.biblioteca.dao.Librarian;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.DAO;
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
    void crud(){
        Librarian l1 = new Librarian("pedromendes",
                "12345", "rua rua", "5259", "Joao");
        Librarian l2 = new Librarian("pedrouou",
                "12345", "rua rua", "5259", "Joao");

        DAO.getLibrarianDAO().create(l1);
        DAO.getLibrarianDAO().create(l2);

        assertEquals(2, DAO.getLibrarianDAO().getAll().size());

        l1 = DAO.getLibrarianDAO().getByPK("pedromendes");

        assertNotNull(l1);

        l1.setName("Joaquim");
        DAO.getLibrarianDAO().update(l1);
        l1 = DAO.getLibrarianDAO().getByPK("pedromendes");

        assertEquals("Joaquim", l1.getName());

        DAO.getLibrarianDAO().deleteByPK("pedromendes");
        l1 = DAO.getLibrarianDAO().getByPK("pedromendes");

        assertNull(l1);
        assertEquals(1, DAO.getLibrarianDAO().getAll().size());
    }

}