package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.Book.BookFileImpl;
import com.pbl.biblioteca.dao.BookCopy.BookCopyFileImpl;
import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.Librarian.LibrarianFileImpl;
import com.pbl.biblioteca.dao.Loan.LoanFileImpl;
import com.pbl.biblioteca.dao.User.UserFileImpl;
import com.pbl.biblioteca.exceptionHandler.CopyNotFoundException;
import com.pbl.biblioteca.exceptionHandler.UserIsBlockedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {
    private final UserFileImpl userDAO = new UserFileImpl();
    private final BookFileImpl bookDAO = new BookFileImpl();
    private final LibrarianFileImpl librarianDAO = new LibrarianFileImpl();
    private final LoanFileImpl loanDAO = new LoanFileImpl();
    private final BookCopyFileImpl bookCopyDAO = new BookCopyFileImpl();

    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();

    }

    @AfterEach
    void tearDown() {
        ConnectionFile.cleanTestFiles();
    }

    @Test
    void createBookLoan() {
        Librarian l1 = new Librarian("librarianmendes", "12345");

        Book b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        Book b2 = new Book("A batida do maneco", "Preto", "Azul",
                2002, "Mistério", "22222");
        Reader u1 = new Reader("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        Reader u2 = new Reader("pedroca22", "Pedroca", "te tete",
                "79585959", "12345");

        b1.addCopies(1);
        b2.addCopies(2);

        librarianDAO.create(l1);
        userDAO.create(u1);
        userDAO.create(u2);
        bookDAO.create(b1);
        bookDAO.create(b2);

        Loan newLoan, newLoan2 = null;
        int exception1 = 0, exception2 = 0;

        try {
            newLoan2 = l1.createBookLoan(b1, u1, 7);
        } catch (UserIsBlockedException e){
            System.out.println(e.getMessage() + "1");
        } catch (CopyNotFoundException d){
            System.out.println(d.getMessage());
        }


        try {
            newLoan = l1.createBookLoan(b1, u1, 7);
        } catch (UserIsBlockedException e){
            System.out.println(e.getMessage());
        } catch (CopyNotFoundException d){
            exception1 = 1;
        }


        u2.blockUser(LocalDate.now().plusDays(3));
        userDAO.update(u2);

        try {
            newLoan = l1.createBookLoan(b1, u2, 7);
        } catch (UserIsBlockedException e){
            exception2 = 1;
        } catch (CopyNotFoundException d){
            System.out.println(d.getMessage() + ".");
        }

        assertEquals(exception1, 1);
        assertEquals(exception2, 1);

        try {
            newLoan = l1.createBookLoan(b2, u1, 7);
        } catch (UserIsBlockedException e){
            System.out.println(e.getMessage());
        } catch (CopyNotFoundException d){
            System.out.println(d.getMessage() + ".");
        }

        assertTrue(bookCopyDAO.getByPK(newLoan2.getBookId()).isBorrowed());
    }

    @Test
    void deleteBookLoan() {
        Librarian l1 = new Librarian("librarianmendes", "12345");

        Book b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111");
        Book b2 = new Book("A batida do maneco", "Preto", "Azul",
                2002, "Mistério", "22222");
        Reader u1 = new Reader("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        Reader u2 = new Reader("pedroca22", "Pedroca", "te tete",
                "79585959", "12345");

        b1.addCopies(1);
        b2.addCopies(2);

        librarianDAO.create(l1);
        userDAO.create(u1);
        userDAO.create(u2);
        bookDAO.create(b1);
        bookDAO.create(b2);

        Loan newLoan = null, newLoan2 = null;

        try {
            newLoan2 = l1.createBookLoan(b1, u1, 7);
        } catch (UserIsBlockedException e){
            System.out.println(e.getMessage() + "1");
        } catch (CopyNotFoundException d){
            System.out.println(d.getMessage() + "1.");
        }

        try {
            newLoan = l1.createBookLoan(b2, u1, 7);
        } catch (UserIsBlockedException |CopyNotFoundException e){
            System.out.println(e.getMessage());
        }


        assertTrue(bookCopyDAO.getByPK(newLoan2.getBookId()).isBorrowed());
        assertTrue(bookCopyDAO.getByPK(newLoan.getBookId()).isBorrowed());

        l1.deleteBookLoan(newLoan2.getId());

        assertFalse(bookCopyDAO.getByPK(newLoan2.getBookId()).isBorrowed());

    }
}