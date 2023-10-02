package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
class LibrarianTest {

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
    void crudBookLoan(){
        Librarian l1 = new Librarian("pedromendes",
                "12345", "rua rua", "5259", "Joao");
        Book b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111", 3);
        Reader r1 = new Reader("r1",
                "12345", "rua rua", "5259", "pedrin");
        Reader r2 = new Reader("r2",
                "12345", "rua rua", "5259", "ped");
        Reader r3 = new Reader("r3",
                "12345", "rua rua", "5259", "pedrooo");
        Reader r4 = new Reader("r4",
                "12345", "rua rua", "5259", "pedropedro");

        DAO.getLibrarianDAO().create(l1);
        DAO.getBookDAO().create(b1);
        DAO.getReaderDAO().create(r1);
        DAO.getReaderDAO().create(r2);
        DAO.getReaderDAO().create(r3);
        DAO.getReaderDAO().create(r4);


        // Criação de empréstimos
        Loan loanR2 = null;
        try {
            l1.createBookLoan(b1, r1, 7);
            loanR2 = l1.createBookLoan(b1, r2, 7);
        }catch (readerIsBlockedException | notFoundException | fullException |
                tooManyReservesException e){
            e.printStackTrace();
        }

        assertEquals(2, DAO.getLoanDAO().getAllFromBook(b1.getIsbn()).size());

        BookReserve bR = new BookReserve(r3.getUsername(), b1.getIsbn());
        DAO.getBookReserveDAO().create(bR);


        // Testando exceção: criar empréstimo com uma reserva feita arbitrariamente
        // Obs.: Foi criada uma reserva "bR" acima, mesmo com cópias livres, para fins de teste
        boolean tooManyReserves = false;
        try {
            l1.createBookLoan(b1, r4, 7);
        }catch (readerIsBlockedException | notFoundException | fullException e){
            e.printStackTrace();
        } catch (tooManyReservesException e){
            tooManyReserves = true;
        }
        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());
        assertTrue(tooManyReserves);

        DAO.getBookReserveDAO().deleteByPK(bR.getId());

        // Deletando empréstimo do r1
        l1.deleteBookLoan(DAO.getLoanDAO().getAllFromUser(r1.getUsername()).get(0).getId());

        assertEquals(0, DAO.getLoanDAO().getAllFromUser(r1.getUsername()).size());
        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());

        try {
            l1.createBookLoan(b1, r3, 7);
            l1.createBookLoan(b1, r4, 7);
        }catch (readerIsBlockedException | notFoundException | fullException |
                tooManyReservesException e){
            e.printStackTrace();
        }

        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());

        assertEquals(3, b1.getTotalCopies());
        assertEquals(0, b1.getAvailableCopies());
        assertEquals(3, DAO.getLoanDAO().getAllFromBook(b1.getIsbn()).size());

        // Testando exceção: criando um empréstimo com todas as cópias emprestadas
        boolean notFoundException = false;
        try {
            l1.createBookLoan(b1, r1, 7);
        }catch (tooManyReservesException | fullException | readerIsBlockedException e){
            e.printStackTrace();
        } catch (notFoundException e){
            notFoundException = true;
        }
        assertTrue(notFoundException);

        l1.deleteBookLoan(loanR2.getId());

        r2.setBlocked(true);
        DAO.getReaderDAO().update(r2);


        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());

        // Testando exceção: usuário bloqueado
        boolean readerIsBlocked = false;
        try {
            l1.createBookLoan(b1, r2, 7);
        }catch (tooManyReservesException | notFoundException | fullException e){
            e.printStackTrace();
        } catch (readerIsBlockedException e){
            readerIsBlocked = true;
        }

        assertTrue(readerIsBlocked);
        r2.setBlocked(false);
        DAO.getReaderDAO().update(r2);
        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());


        try {
            b1.removeCopies(1);
        } catch (notFoundException e){
            e.printStackTrace();
        }

        try {
            r1.createBookReserve(b1);
            Thread.sleep(1001);
            r2.createBookReserve(b1);
        } catch (readerIsBlockedException |fullException |
                copyAvailableException | InterruptedException e){
            e.printStackTrace();
        }

        // Deletando todos os empréstimos
        ArrayList<Loan> loans = DAO.getLoanDAO().getAllFromBook(b1.getIsbn());
        for (Loan l : loans){
            l1.deleteBookLoan(l.getId());
        }
        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());

        assertEquals(2, b1.getTotalCopies());
        assertEquals(2, b1.getAvailableCopies());
        assertEquals(0, DAO.getLoanDAO().getAllFromBook(b1.getIsbn()).size());

        // Testando exceção: mesmo com 2 cópias disponíveis, o r4 e o r3 não podem emprestar
        // Já que existem 2 outros leitores na lista de reservas
        tooManyReserves = false;
        try {
            l1.createBookLoan(b1, r4, 7);
        }catch (notFoundException | readerIsBlockedException | fullException e){
            e.printStackTrace();
        } catch (tooManyReservesException e){
            tooManyReserves = true;
        }

        assertTrue(tooManyReserves);

        tooManyReserves = false;
        try {
            l1.createBookLoan(b1, r3, 7);
        }catch (notFoundException | readerIsBlockedException | fullException e){
            e.printStackTrace();
        } catch (tooManyReservesException e){
            tooManyReserves = true;
        }

        assertTrue(tooManyReserves);

        try {
            b1.removeCopies(1);
        } catch (notFoundException e){
            e.printStackTrace();
        }

        // Como foi removida uma cópia do livro acima, apenas uma restou
        // Então o r2, apesar de estar na lista de reservas, está em segundo
        // Sendo assim, ele não deve poder emprestar o livro
        tooManyReserves = false;
        try {
            l1.createBookLoan(b1, r2, 7);
        }catch (notFoundException | readerIsBlockedException | fullException e){
            e.printStackTrace();
        } catch (tooManyReservesException e){
            tooManyReserves = true;
        }
        assertTrue(tooManyReserves);

        b1.addCopies(1);

        // Agora foi adicionada uma nova cópia ao livro, dessa forma
        // o r2, mesmo estando na segunda posição da fila, deve poder emprestar o livro,
        // pois existem 2 cópias livres
        try {
            l1.createBookLoan(b1, r2, 7);
            tooManyReserves = false;
        }catch (notFoundException | readerIsBlockedException | fullException |
                tooManyReservesException e){
            e.printStackTrace();
        }
        assertFalse(tooManyReserves);


        tooManyReserves = true;
        try {
            l1.createBookLoan(b1, r1, 7);
            tooManyReserves = false;
        }catch (notFoundException | readerIsBlockedException | fullException |
                tooManyReservesException e){
            e.printStackTrace();
        }

        assertFalse(tooManyReserves);
    }


    @Test
    void searchByTitle(){
        Librarian l1 = new Librarian("pedromendes",
                "12345", "rua rua", "5259", "Joao");

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

        ArrayList<Book> like = l1.searchBookByTitle("A viagem de");

        assertEquals(2, like.size());

        like = l1.searchBookByTitle("A coNveRsA");

        assertEquals(3, like.size());
        assertEquals("A conversa", like.get(0).getTitle());

        like = l1.searchBookByTitle("A vIaGeM dE coiSiNho");

        assertEquals(1, like.size());
    }
}