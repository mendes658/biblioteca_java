package com.pbl.biblioteca.dao.Book;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Book;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class BookDAOImplTest {


    @BeforeEach
    void setUp() {
        ConnectionDAO.setTestFileUrls();
    }

    @AfterEach
    void tearDown() {
        ConnectionDAO.cleanTestFiles();
    }

    @Test

    void createAndGet() {
        Book b1, b2;
        BookDAOImpl bookDAO = new BookDAOImpl();

        b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        b2 = new Book("A batida do maneco", "Preto", "Azul",
                2002, "Mistério", "22222");

        bookDAO.create(b1);
        bookDAO.create(b2);

        assertEquals(b1.getTitle(), bookDAO.getByPK("11111").getTitle());
        assertEquals(b1.getCategory(), bookDAO.getByPK("11111").getCategory());
        assertEquals(b1.getYear(), bookDAO.getByPK("11111").getYear());
        assertEquals(b1.getPublisher(), bookDAO.getByPK("11111").getPublisher());
        assertEquals(b2.getTitle(), bookDAO.getByPK("22222").getTitle());
    }


    @Test
    void update() {
        Book b1, b2;
        BookDAOImpl bookDAO = new BookDAOImpl();

        b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        b2 = new Book("A viagem de", "Amarelo", "Vermelho",
                2002, "Mistério", "22222");

        bookDAO.create(b1);
        bookDAO.create(b2);

        b1.setTitle("Trafegando por aí");
        b1.setCategory("Ação");

        bookDAO.update(b1);

        assertEquals(bookDAO.getByPK("11111").getTitle(), "Trafegando por aí");
        assertEquals(bookDAO.getByPK("11111").getCategory(), "Ação");

        assertEquals(bookDAO.getByPK("22222").getTitle(), "A viagem de");
        assertEquals(bookDAO.getByPK("22222").getCategory(), "Mistério");
    }


    @Test
    void deleteAndGetBooksByCategory() {
        Book b1, b2, b4;
        BookDAOImpl bookDAO = new BookDAOImpl();

        b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        b2 = new Book("A viagem de", "Amarelo", "Vermelho",
                2002, "Mistério", "22222");
        b4 = new Book("O fim do começo", "Amarelo", "Vermelho",
                2002, "Terror", "44444");

        bookDAO.create(b1);
        bookDAO.create(b2);
        bookDAO.create(b4);

        b1.setTitle("Trafegando por aí");
        b1.setCategory("Ação");

        bookDAO.update(b1);
        bookDAO.deleteByPK("44444");

        assertEquals(bookDAO.getAllBooksFromCategory("Ação").size(), 1);
        assertEquals(bookDAO.getAllBooksFromCategory("Mistério").size(), 1);
        assertEquals(bookDAO.getAllBooksFromCategory("Terror").size(), 0);

    }

    @Test
    void getAll() {
        Book b1, b2;
        BookDAOImpl bookDAO = new BookDAOImpl();

        b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        b2 = new Book("A batida do maneco", "Preto", "Azul",
                2002, "Mistério", "22222");

        bookDAO.create(b1);
        bookDAO.create(b2);

        HashMap<String, Book> all = bookDAO.getAll();
        assertEquals(all.get("11111").getTitle(), "Teco teleco teco");
        assertEquals(all.get("22222").getTitle(), "A batida do maneco");
    }


}