package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {
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
    void searchByTitle(){
        Guest g1 = new Guest();

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

        ArrayList<Book> like = g1.searchBookByTitle("A viagem de");

        assertEquals(2, like.size());

        like = g1.searchBookByTitle("A coNveRsA");

        assertEquals(3, like.size());
        assertEquals("A conversa", like.get(0).getTitle());

        like = g1.searchBookByTitle("A vIaGeM dE coiSiNho");

        assertEquals(1, like.size());
    }
}