package com.pbl.biblioteca.dao.Reader;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.model.Librarian;
import com.pbl.biblioteca.model.Reader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderDAOTest {
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
    void crud(){
        Reader r1 = new Reader("pedromendes",
                "12345", "rua rua", "5259", "Joao");
        Reader r2 = new Reader("pedrouou",
                "12345", "rua rua", "5259", "Joao");

        DAO.getReaderDAO().create(r1);
        DAO.getReaderDAO().create(r2);

        assertEquals(2, DAO.getReaderDAO().getAll().size());

        r1 = DAO.getReaderDAO().getByPK("pedromendes");

        assertNotNull(r1);

        r1.setName("Joaquim");
        DAO.getReaderDAO().update(r1);
        r1 = DAO.getReaderDAO().getByPK("pedromendes");

        assertEquals("Joaquim", r1.getName());

        DAO.getReaderDAO().deleteByPK("pedromendes");
        r1 = DAO.getReaderDAO().getByPK("pedromendes");

        assertNull(r1);
        assertEquals(1, DAO.getReaderDAO().getAll().size());
    }

}