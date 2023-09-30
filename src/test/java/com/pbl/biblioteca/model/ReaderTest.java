package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.copyAvailableException;
import com.pbl.biblioteca.exceptionHandler.fullException;
import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.readerIsBlockedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}