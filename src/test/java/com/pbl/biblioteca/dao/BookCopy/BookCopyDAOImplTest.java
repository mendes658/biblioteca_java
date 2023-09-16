package com.pbl.biblioteca.dao.BookCopy;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.BookCopy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BookCopyDAOImplTest {

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
        BookCopyFileImpl bookCopyDAO = new BookCopyFileImpl();
        BookCopy b1 = new BookCopy("Opa opa", "Laranja", "Camelo", 2005,
                "Terror", "12345");
        bookCopyDAO.create(b1);
        assertEquals(bookCopyDAO.getByPK(b1.getId()).getCategory(), "Terror");
    }

    @Test
    void update() {
        BookCopyFileImpl bookCopyDAO = new BookCopyFileImpl();
        BookCopy b1 = new BookCopy("Opa opa", "Laranja", "Camelo", 2005,
                "Terror", "12345");
        bookCopyDAO.create(b1);
        b1.setAuthor("Mane");
        bookCopyDAO.update(b1);
        assertEquals(bookCopyDAO.getByPK(b1.getId()).getAuthor(), "Mane");
    }

    @Test
    void deleteByPK() {
        BookCopyFileImpl bookCopyDAO = new BookCopyFileImpl();
        BookCopy b1 = new BookCopy("Opa opa", "Laranja", "Camelo", 2005,
                "Terror", "12345");
        bookCopyDAO.create(b1);
        bookCopyDAO.deleteByPK(b1.getId());
        assertNull(bookCopyDAO.getByPK(b1.getId()));
    }

    @Test
    void getAll() {
        BookCopyFileImpl bookCopyDAO = new BookCopyFileImpl();
        BookCopy b1 = new BookCopy("Opa opa", "Laranja", "Camelo", 2005,
                "Terror", "12345");
        BookCopy b2 = new BookCopy("Opa opa22", "Laranja22", "Camelo22", 2005,
                "Terror22", "12345");
        bookCopyDAO.create(b1);
        bookCopyDAO.create(b2);
        HashMap<String, BookCopy> bookCopyHM = bookCopyDAO.getAll();
        assertEquals(bookCopyHM.get(b1.getId()).getCategory(), "Terror");
        assertEquals(bookCopyHM.get(b2.getId()).getCategory(), "Terror22");
    }
}