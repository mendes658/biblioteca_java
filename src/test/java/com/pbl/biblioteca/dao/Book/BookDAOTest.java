package com.pbl.biblioteca.dao.Book;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.model.Book;
import org.junit.jupiter.api.*;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOTest {


    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();
        ConnectionMemory.clearMemory();
        ConnectionFile.clearTestFiles();
    }

    @AfterEach
    void tearDown() {
        ConnectionFile.setDefaultFileUrls();
        ConnectionFile.clearTestFiles();
        ConnectionMemory.clearMemory();
    }

    @Test
    void crud(){
        Book b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111", 2);
        Book b2 = new Book("A batida do maneco", "Preto", "Azul",
                2002, "Mistério", "22222", 2);

        DAO.getBookDAO().create(b1);
        DAO.getBookDAO().create(b2);

        assertEquals(2, DAO.getBookDAO().getAll().size());

        b1.setTitle("Atualizou");

        DAO.getBookDAO().update(b1);

        assertNotNull(DAO.getBookDAO().getByPK("22222"));
        assertEquals("Atualizou", DAO.getBookDAO().getByPK("11111").getTitle());

        DAO.getBookDAO().deleteByPK("22222");

        assertNull(DAO.getBookDAO().getByPK("22222"));
    }

    @Test
    void getAllBooksFromCategory(){
        Book b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111", 2);
        Book b2 = new Book("A batida do maneco", "Preto", "Azul",
                2002, "Mistério", "22222", 2);
        Book b3 = new Book("A batida do maneco", "Preto", "Azul",
                2002, "Ação", "33333", 2);

        DAO.getBookDAO().create(b1);
        DAO.getBookDAO().create(b2);
        DAO.getBookDAO().create(b3);

        ArrayList<Book> misterio = DAO.getBookDAO().getAllBooksFromCategory("Mistério");
        ArrayList<Book> acao = DAO.getBookDAO().getAllBooksFromCategory("Ação");
        ArrayList<Book> wrong = DAO.getBookDAO().getAllBooksFromCategory("Terror");

        assertEquals(2, misterio.size());
        assertEquals(1, acao.size());
        assertEquals(0, wrong.size());
    }

    @Test
    void searchByTitle(){
        Book b1 = new Book("A viagem de coisinho", "Amarelo", "Vermelho",
                2002, "Mistério", "11111", 2);
        Book b2 = new Book("A viagem de coisão", "Preto", "Azul",
                2002, "Mistério", "22222", 2);
        Book b3 = new Book("A conversa fiada 2", "Preto", "Azul",
                2002, "Ação", "33333", 2);
        Book b4 = new Book("A conversa fiada", "Preto", "Azul",
                2002, "Ação", "44444", 2);
        Book b5 = new Book("A conversa", "Preto", "Azul",
                2002, "Ação", "55555", 2);

        DAO.getBookDAO().create(b1);
        DAO.getBookDAO().create(b2);
        DAO.getBookDAO().create(b3);
        DAO.getBookDAO().create(b4);
        DAO.getBookDAO().create(b5);

        ArrayList<Book> like = DAO.getBookDAO().searchByTitle("A viagem de");

        assertEquals(2, like.size());

        like = DAO.getBookDAO().searchByTitle("A conversa");

        assertEquals(3, like.size());
        assertEquals("a conversa", like.get(0).getTitle());

        like = DAO.getBookDAO().searchByTitle("A vIaGeM dE coiSiNho");

        assertEquals(1, like.size());
    }

}