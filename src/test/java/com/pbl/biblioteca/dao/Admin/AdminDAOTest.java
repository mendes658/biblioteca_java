package com.pbl.biblioteca.dao.Admin;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.model.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminDAOTest {
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
    void crud(){
        Admin a1 = new Admin("pedromendes",
                "12345", "rua rua", "5259", "Joao");
        Admin a2 = new Admin("pedrouou",
                "12345", "rua rua", "5259", "Joao");

        DAO.getAdminDAO().create(a1);
        DAO.getAdminDAO().create(a2);

        assertEquals(2, DAO.getAdminDAO().getAll().size());

        a1 = DAO.getAdminDAO().getByPK("pedromendes");

        assertNotNull(a1);

        a1.setName("Joaquim");
        DAO.getAdminDAO().update(a1);
        a1 = DAO.getAdminDAO().getByPK("pedromendes");

        assertEquals("Joaquim", a1.getName());

        DAO.getAdminDAO().deleteByPK("pedromendes");
        a1 = DAO.getAdminDAO().getByPK("pedromendes");

        assertNull(a1);
        assertEquals(1, DAO.getAdminDAO().getAll().size());
    }
}