package com.pbl.biblioteca.dao.User;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Reader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderFileImplTest {

    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();
    }

    @AfterEach
    void tearDown() {
        ConnectionFile.setDefaultFileUrls();
    }

    @Test
    void create() {
        UserFileImpl userDAO = new UserFileImpl();
        Reader u1 = new Reader("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        userDAO.create(u1);
        assertEquals(userDAO.getByPK("pedromendes").getName(), "Pedro Mendes");
    }

    @Test
    void getByPK() {
        UserFileImpl userDAO = new UserFileImpl();
        Reader u1 = new Reader("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        userDAO.create(u1);
        assertEquals(userDAO.getAll().get("pedromendes").getName(), "Pedro Mendes");
    }

    @Test
    void update() {
        UserFileImpl userDAO = new UserFileImpl();
        Reader u1 = new Reader("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        userDAO.create(u1);
        u1.setName("Jacaré");
        userDAO.update(u1);
        assertEquals(userDAO.getByPK("pedromendes").getName(), "Jacaré");
    }

    @Test
    void deleteByPK() {
        UserFileImpl userDAO = new UserFileImpl();
        Reader u1 = new Reader("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        userDAO.create(u1);
        userDAO.deleteByPK("pedromendes");
        assertNull(userDAO.getByPK("pedromendes"));
    }

    @Test
    void generateId() {
        UserFileImpl userDAO = new UserFileImpl();
        int a = Integer.parseInt(userDAO.generateId());
        int b = Integer.parseInt(userDAO.generateId());
        assertTrue(b > a);
    }

    @Test
    void getAll() {
        UserFileImpl userDAO = new UserFileImpl();
        Reader u1 = new Reader("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        userDAO.create(u1);
        assertEquals(userDAO.getAll().get("pedromendes").getName(), "Pedro Mendes");
    }
}