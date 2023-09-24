package com.pbl.biblioteca.dao.BookReserve;


import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.BookReserve;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BookReserveDAOImplTest {
    private final BookReserveFileImpl bookReserveDAO = new BookReserveFileImpl();

    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();
    }

    @AfterEach
    void tearDown() {
        ConnectionFile.cleanTestFiles();
    }

    @Test
    void createAndGetAll() {
        BookReserve res1 = new BookReserve("pedromendes", "12345");
        BookReserve res2 = new BookReserve("mendes", "12345");

        bookReserveDAO.create(res1);
        bookReserveDAO.create(res2);

        HashMap<String, BookReserve> all = bookReserveDAO.getAll();
        assertTrue(all.containsKey(res1.getId()));
        assertEquals(all.get(res1.getId()).getBookIsbn(), "12345");
    }

    @Test
    void deleteByPK() {
        BookReserve res1 = new BookReserve("pedromendes", "12345");
        BookReserve res2 = new BookReserve("mendes", "12345");
        BookReserve res3 = new BookReserve("mendes", "55555");

        bookReserveDAO.create(res1);
        bookReserveDAO.create(res2);
        bookReserveDAO.create(res3);


        assertEquals(bookReserveDAO.getAll().size(), 3);

        bookReserveDAO.deleteByPK("12345");

        assertEquals(bookReserveDAO.getAll().size(), 1);

    }

    @Test
    void update() {
        BookReserve res1 = new BookReserve("pedromendes", "12345");
        BookReserve res2 = new BookReserve("mendes", "12345");

        bookReserveDAO.create(res1);
        bookReserveDAO.create(res2);

        assertEquals(bookReserveDAO.getAll().get(res1.getId()).getUsername(), "pedromendes");
        assertTrue(bookReserveDAO.getAll().containsKey(res1.getId()));
        res1.setUsername("cavalo");
        bookReserveDAO.update(res1);

        assertTrue(bookReserveDAO.getAll().containsKey(res1.getId()));
        assertEquals(bookReserveDAO.getAll().get(res1.getId()).getUsername(), "cavalo");
    }

    @Test
    void getByPKAndGenerateId() {
        BookReserve res1 = new BookReserve("pedromendes", "12345");
        BookReserve res2 = new BookReserve("mendes", "12345");

        bookReserveDAO.create(res1);
        bookReserveDAO.create(res2);

        assertEquals(bookReserveDAO.getByPK(res1.getId()).getUsername(), "pedromendes");
    }

    @Test
    void getReservesFromBook() {
        BookReserve res1 = new BookReserve("pedromendes", "12345");
        BookReserve res2 = new BookReserve("mendes", "12345");
        BookReserve res3 = new BookReserve("mendes", "55555");

        bookReserveDAO.create(res1);
        bookReserveDAO.create(res2);
        bookReserveDAO.create(res3);

        ArrayList<BookReserve> reserves = bookReserveDAO.getReservesFromBook("12345");
        assertEquals(reserves.size(), 2);


        reserves = bookReserveDAO.getReservesFromBook("55555");
        assertEquals(reserves.size(), 1);
    }

    @Test
    void removeAllFromUser() {
        BookReserve res1 = new BookReserve("pedromendes", "12345");
        BookReserve res2 = new BookReserve("mendes", "12345");
        BookReserve res3 = new BookReserve("mendes", "55555");

        bookReserveDAO.create(res1);
        bookReserveDAO.create(res2);
        bookReserveDAO.create(res3);

        assertEquals(bookReserveDAO.getAll().size(), 3);

        bookReserveDAO.removeAllFromUser("mendes");

        assertEquals(bookReserveDAO.getAll().size(), 1);
        assertNotNull(bookReserveDAO.getAll().get(res1.getId()));
        assertNull(bookReserveDAO.getAll().get(res2.getId()));
        assertNull(bookReserveDAO.getAll().get(res3.getId()));
    }

    @Test
    void removeReserve() {
        BookReserve res1 = new BookReserve("pedromendes", "12345");
        BookReserve res2 = new BookReserve("mendes", "12345");
        BookReserve res3 = new BookReserve("mendes", "55555");

        bookReserveDAO.create(res1);
        bookReserveDAO.create(res2);
        bookReserveDAO.create(res3);
    }

    @Test
    void getAllFromUser() {
        BookReserve res1 = new BookReserve("pedromendes", "12345");
        BookReserve res2 = new BookReserve("mendes", "12345");
        BookReserve res3 = new BookReserve("mendes", "55555");

        bookReserveDAO.create(res1);
        bookReserveDAO.create(res2);
        bookReserveDAO.create(res3);

        assertEquals(bookReserveDAO.getAllFromReader("mendes").size(), 2);
        assertEquals(bookReserveDAO.getAllFromReader("pedromendes").size(), 1);
    }
}