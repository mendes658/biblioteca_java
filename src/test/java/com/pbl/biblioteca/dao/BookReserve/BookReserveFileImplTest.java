package com.pbl.biblioteca.dao.BookReserve;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.model.BookReserve;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.pbl.biblioteca.dao.ConnectionFile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BookReserveFileImplTest {

    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();
    }

    @AfterEach
    void tearDown() {
        ConnectionFile.cleanTestFiles();
    }

    @Test
    void crud(){
        BookReserve r1 = new BookReserve("pedrom", "2222");
        BookReserve r2 = new BookReserve("pedped", "2222");

        DAO.getBookReserveDAO().create(r1);
        DAO.getBookReserveDAO().create(r2);

        r1 = DAO.getBookReserveDAO().getByPK(r1.getId());

        assertEquals(2, DAO.getBookReserveDAO().getAll().size());
        assertNotNull(r1);

        r1.setUsername("pedrop");
        DAO.getBookReserveDAO().update(r1);

        assertEquals("pedrop", DAO.getBookReserveDAO().getByPK(r1.getId()).getUsername());

        DAO.getBookReserveDAO().deleteByPK(r1.getId());

        assertNull(DAO.getBookReserveDAO().getByPK(r1.getId()));
        assertEquals(1, DAO.getBookReserveDAO().getAll().size());
    }

    @Test
    void removeAndGetAllFromReader(){
        BookReserve r1 = new BookReserve("pedrom", "2222");
        BookReserve r2 = new BookReserve("pedrom", "3333");
        BookReserve r3 = new BookReserve("pacopaco", "2222");
        BookReserve r4 = new BookReserve("pedrom", "5555");

        DAO.getBookReserveDAO().create(r1);
        DAO.getBookReserveDAO().create(r2);
        DAO.getBookReserveDAO().create(r3);
        DAO.getBookReserveDAO().create(r4);

        ArrayList<BookReserve> fromUser = DAO.getBookReserveDAO().getAllFromReader("pedrom");

        assertEquals(3, fromUser.size());

        DAO.getBookReserveDAO().removeAllFromReader("pedrom");
        fromUser = DAO.getBookReserveDAO().getAllFromReader("pedrom");

        assertEquals(0, fromUser.size());
        assertEquals(1, DAO.getBookReserveDAO().getAll().size());
    }

    @Test
    void getReservesFromBookAndByBook() throws InterruptedException{
        BookReserve r1 = new BookReserve("pedrom", "2222");
        Thread.sleep(1200);
        BookReserve r2 = new BookReserve("pedrom", "3333");
        Thread.sleep(1200);
        BookReserve r3 = new BookReserve("pacopaco", "2222");
        Thread.sleep(1200);
        BookReserve r4 = new BookReserve("goiabada", "2222");

        DAO.getBookReserveDAO().create(r1);
        DAO.getBookReserveDAO().create(r2);
        DAO.getBookReserveDAO().create(r3);
        DAO.getBookReserveDAO().create(r4);

        ArrayList<BookReserve> queue = DAO.getBookReserveDAO().getReservesFromBook("2222");
        assertEquals("pedrom", queue.get(0).getUsername());
        assertEquals("goiabada", queue.get(2).getUsername());

        HashMap<String, ArrayList<BookReserve>> byBook = DAO.getBookReserveDAO().getAllByBook();
        ArrayList<BookReserve> queue2 = byBook.get("2222");

        assertEquals("pedrom", queue2.get(0).getUsername());
        assertEquals("goiabada", queue2.get(2).getUsername());

        assertNotNull(byBook.get("3333"));
    }


}