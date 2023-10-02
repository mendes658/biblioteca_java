package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.alreadyRenewedException;
import com.pbl.biblioteca.exceptionHandler.tooManyReservesException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
class LoanTest {

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
    void renew(){
        Loan l1 = new Loan("2222", "pedrom", 7, "mendes");

        try{
            l1.renew();
        } catch (alreadyRenewedException | tooManyReservesException e){
            e.printStackTrace();
        }

        boolean alreadyRenewed = false;
        try{
            l1.renew();
        } catch (tooManyReservesException e){
            e.printStackTrace();
        } catch (alreadyRenewedException e ){
            alreadyRenewed = true;
        }

        assertTrue(alreadyRenewed);
        assertEquals(l1.getFinalDate(), LocalDate.now().plusDays(7));
    }
}