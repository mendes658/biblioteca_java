package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

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
        Admin adm = new Admin("pedro", "12345", "rua", "75", "pedrom");
        Reader r1 = null;
        Admin a1 = null;
        Librarian l1 = null;

        try {
            r1 = (Reader) adm.createUser("pedrom", "12345",
                    "rua", "75", "mendes", "reader");
            a1 = (Admin) adm.createUser("pedrom", "12345",
                    "rua", "75", "mendes", "admin");
            l1 = (Librarian) adm.createUser("pedrom", "12345",
                    "rua", "75", "mendes", "librarian");

        } catch (usernameAlreadyInUseException e){
            e.printStackTrace();
        }

        assertNotNull(r1);
        assertNotNull(a1);
        assertNotNull(l1);

        a1 = DAO.getAdminDAO().getByPK(a1.getUsername());
        l1 = DAO.getLibrarianDAO().getByPK(l1.getUsername());

        assertNotNull(a1);
        assertNotNull(l1);

        adm.deleteUser(a1);
        adm.deleteUser(l1);
        r1.setBlocked(true);
        adm.updateUser(r1);

        try {
            a1 = (Admin) adm.getUser(a1.getUsername(), "admin");
            l1 = (Librarian) adm.getUser(l1.getUsername(), "librarian");
        } catch (notFoundException e){
            e.printStackTrace();
        }

        assertNotNull(r1);
        assertNull(a1);
        assertNull(l1);
        assertTrue(DAO.getReaderDAO().getByPK(r1.getUsername()).getBlocked());
    }

    @Test
    void blockAndUnblockReader(){
        Admin adm = new Admin("pedro", "12345", "rua", "75", "pedrom");
        Reader r1 = null;
        try {
            r1 = (Reader) adm.createUser("pedrom", "12345",
                    "rua", "75", "mendes", "reader");
        } catch (usernameAlreadyInUseException e){
            e.printStackTrace();
        }

        assertFalse(r1.getBlocked());

        adm.blockReader(r1, 7);
        r1 = DAO.getReaderDAO().getByPK(r1.getUsername());

        assertTrue(r1.getBlocked());
        assertEquals(LocalDate.now().plusDays(7).toString(), r1.getDateEndBlock().toString());

        adm.unblockReader(r1);
        r1 = DAO.getReaderDAO().getByPK(r1.getUsername());

        assertFalse(r1.getBlocked());
        assertNull(r1.getDateEndBlock());
    }

    @Test
    void crudBookAndCopies(){
        Admin adm = new Admin("pedro", "12345", "rua", "75", "pedrom");
        Book b1 = null;
        Librarian l1 = null;
        Reader r1 = null;
        Reader r2 = null;
        Reader r3 = null;


        try {
            b1 = adm.createBook("Teco teleco teco", "Amarelo", "Vermelho",
                    2002, "Mistério", "11111", 2);
            l1 = (Librarian) adm.createUser("pedro", "12345",
                    "rua", "75", "pedrom", "librarian");
            r1 = (Reader) adm.createUser("pedro25", "12345",
                    "rua", "75", "pedrom", "reader");
            r2 = (Reader) adm.createUser("pedro26", "12345",
                    "rua", "75", "pedrom", "reader");
            r3 = (Reader) adm.createUser("pedro27", "12345",
                    "rua", "75", "pedrom", "reader");

        } catch (isbnAlreadyInUseException | usernameAlreadyInUseException e){
            e.printStackTrace();
        }

        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());

        assertEquals(2, b1.getTotalCopies());

        adm.addBookCopies(b1, 3);
        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());

        assertEquals(5, b1.getTotalCopies());

        try {
            l1.createBookLoan(b1, r1, 7);
            l1.createBookLoan(b1, r2, 7);
            l1.createBookLoan(b1, r3, 7);
        }catch (readerIsBlockedException | notFoundException | fullException |
                    tooManyReservesException e){
            e.printStackTrace();
        }

        try {
            adm.removeBookCopies(b1, 1);
        } catch (notFoundException e){
            e.printStackTrace();
        }


        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());
        assertEquals(4, b1.getTotalCopies());
        assertEquals(1, b1.getAvailableCopies());

        boolean notFound = false;
        try {
            adm.removeBookCopies(b1, 2);
        } catch (notFoundException e){
            notFound = true;
        }

        assertTrue(notFound);

        b1.setTitle("Capim");
        adm.updateBook(b1);

        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());
        assertEquals("Capim", b1.getTitle());

        adm.deleteBook(b1);
        b1 = DAO.getBookDAO().getByPK(b1.getIsbn());

        assertNull(b1);
    }
}