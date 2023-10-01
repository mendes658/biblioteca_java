package com.pbl.biblioteca.dao.User;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.wrongPasswordException;
import com.pbl.biblioteca.model.LocalSystem;
import com.pbl.biblioteca.model.Reader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMemoryAndFileTest {
    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();
        ConnectionMemory.clearMemory();
        ConnectionFile.clearTestFiles();
    }

    @AfterEach
    void tearDown() {
        ConnectionFile.setDefaultFileUrls();
        ConnectionMemory.clearMemory();
        ConnectionFile.clearTestFiles();
    }

    @Test
    void login(){
        Reader r1 = new Reader("pedro", "pedrom",
                "rua", "75", "12345");
        DAO.getReaderDAO().create(r1);
        Reader r2 = null;

        boolean wrongPass = false;
        try {
            r2 = (Reader) DAO.getUserDAO().login("pedro", "1234", "reader");
        } catch (wrongPasswordException e){
            wrongPass = true;
        } catch (notFoundException e){
            e.printStackTrace();
        }

        assertTrue(wrongPass);

        try {
            r2 = (Reader) LocalSystem.login("pedro", "12345", "reader");
        } catch (wrongPasswordException | notFoundException e){
            e.printStackTrace();
        }

        assertNotNull(r2);
        assertEquals("pedro", r2.getUsername());
    }
}