package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
class ReaderTest {

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
    void crudReserve(){
        Book b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111", 0);
        Reader r1 = new Reader("r1",
                "12345", "rua rua", "5259", "pedrin");

        r1.setBlocked(true);

        DAO.getBookDAO().create(b1);
        DAO.getReaderDAO().create(r1);

        boolean isBlocked = false;
        try{
            r1.createBookReserve(b1);
        } catch(fullException |
                copyAvailableException e){
            e.printStackTrace();
        } catch (readerIsBlockedException e){
            isBlocked = true;
        }
        assertTrue(isBlocked);

        r1.setBlocked(false);
        DAO.getReaderDAO().update(r1);

        Loan l = new Loan(b1.getIsbn(), r1.getUsername(), 7, "opa");
        DAO.getLoanDAO().create(l);

        boolean alreadyLoan = false;
        try{
            r1.createBookReserve(b1);
        } catch(fullException |
                copyAvailableException e){
            e.printStackTrace();
        } catch (readerIsBlockedException e){
            alreadyLoan = true;
        }

        assertTrue(alreadyLoan);

        DAO.getLoanDAO().deleteByPK(l.getId());

        try{
            r1.createBookReserve(b1);
        } catch(fullException | readerIsBlockedException |
                copyAvailableException e){
            e.printStackTrace();
        }

        assertNotNull(DAO.getBookReserveDAO().getAllFromReader(r1.getUsername()).get(0));

        boolean alreadyReserve = false;
        try{
            r1.createBookReserve(b1);
        } catch(fullException |
                copyAvailableException e){
            e.printStackTrace();
        } catch (readerIsBlockedException e){
            alreadyReserve = true;
        }

        assertTrue(alreadyReserve);


        b1.addCopies(1);
        DAO.getBookDAO().update(b1);

        boolean thereIsCopy = false;
        try{
            r1.createBookReserve(b1);
        } catch(fullException |
                copyAvailableException e){
            e.printStackTrace();
        } catch (readerIsBlockedException e){
            thereIsCopy = true;
        }

        assertTrue(thereIsCopy);

        try {
            r1.removeReserve(b1);
        } catch (notFoundException e) {
            e.printStackTrace();
        }

        assertEquals(0, DAO.getBookReserveDAO().getAllFromReader(r1.getUsername()).size());
    }

    @Test
    void renew(){
        Loan l1 = new Loan("2222", "pedrom", 7, "mendes");
        Reader r1 = new Reader("pedrom",
                "12345", "rua rua", "5259", "pedrin");

        DAO.getLoanDAO().create(l1);
        DAO.getReaderDAO().create(r1);

        try{
            r1.renewLoan(l1);
        } catch (alreadyRenewedException | tooManyReservesException e){
            e.printStackTrace();
        }

        boolean alreadyRenewed = false;
        try{
            r1.renewLoan(l1);
        } catch (tooManyReservesException e){
            e.printStackTrace();
        } catch (alreadyRenewedException e ){
            alreadyRenewed = true;
        }

        assertTrue(alreadyRenewed);
        assertEquals(l1.getFinalDate(), LocalDate.now().plusDays(7));
    }

    @Test
    void searchBook(){
        Reader r1 = new Reader("pedrom",
                "12345", "rua rua", "5259", "pedrin");

        Book b1 = new Book("A viagem de coisinho", "Amarelo", "Vermelho",
                2002, "Mistério", "11111", 2);
        Book b2 = new Book("A viagem de coisão", "Preto", "Azul",
                2002, "Mistério", "22222", 2);
        Book b3 = new Book("A conversa fiada 2", "Verde", "Azul",
                2002, "Ação", "33333", 2);
        Book b4 = new Book("A conversa fiada", "Azul", "Azul",
                2002, "Ação", "44444", 2);
        Book b5 = new Book("A conversa", "Verde", "Azul",
                2002, "Ação", "55555", 2);

        DAO.getBookDAO().create(b1);
        DAO.getBookDAO().create(b2);
        DAO.getBookDAO().create(b3);
        DAO.getBookDAO().create(b4);
        DAO.getBookDAO().create(b5);

        ArrayList<Book> like = r1.searchBookByTitle("A viagem de");

        assertEquals(2, like.size());

        like = r1.searchBookByTitle("A coNveRsA");

        assertEquals(3, like.size());
        assertEquals("A conversa", like.get(0).getTitle());

        like = r1.searchBookByTitle("A vIaGeM dE coiSiNho");

        assertEquals(1, like.size());

        like = r1.searchBookByAuthor("verd");

        assertEquals(2, like.size());

        like = r1.searchBookByCategory("ação");

        assertEquals(3, like.size());

        like = r1.searchBookByIsbn("2222");

        assertEquals(1, like.size());
    }
}