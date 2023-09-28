package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.exceptionHandler.notFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

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

        assertEquals(2, b1.getAvailableCopies());
        assertEquals(2, b1.getTotalCopies());

        b1.addCopies(5);

        try {
            b1.borrowCopy();
        } catch (notFoundException e){
            e.printStackTrace();
        }

        assertEquals(6, b1.getAvailableCopies());
        assertEquals(7, b1.getTotalCopies());

        boolean notFound = false;
        try {
            b1.removeCopies(7);
        } catch (notFoundException e){
            notFound = true;
        }

        assertTrue(notFound);
        b1.retrieveCopy();

        assertEquals(7, b1.getAvailableCopies());
        assertEquals(7, b1.getTotalCopies());

        try {
            b1.removeCopies(7);
        } catch (notFoundException e){
            e.printStackTrace();
        }

        assertEquals(0, b1.getAvailableCopies());
        assertEquals(0, b1.getTotalCopies());
    }

}