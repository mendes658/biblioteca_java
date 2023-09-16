package com.pbl.biblioteca.dao.Operator;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Operator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OperatorDAOImplTest {

    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();
    }

    @AfterEach
    void tearDown() {
        ConnectionFile.setDefaultFileUrls();
    }

    @Test
    void create() {
        OperatorFileImpl operatorDAO = new OperatorFileImpl();
        Operator o1 = new Operator("admin", "11111");
        operatorDAO.create(o1);
        assertEquals(operatorDAO.getByPK("admin").getUsername(), "admin");
    }

    @Test
    void getByPK() {
        OperatorFileImpl operatorDAO = new OperatorFileImpl();
        Operator o1 = new Operator("admin", "11111");
        operatorDAO.create(o1);
        assertEquals(operatorDAO.getByPK("admin").getId(), o1.getId());
    }

    @Test
    void getAll() {
        OperatorFileImpl operatorDAO = new OperatorFileImpl();
        Operator o1 = new Operator("admin", "11111");
        operatorDAO.create(o1);
        HashMap<String, Operator> all = operatorDAO.getAll();
        assertEquals(all.get("admin").getId(), o1.getId());
    }

    @Test
    void update() {
        OperatorFileImpl operatorDAO = new OperatorFileImpl();
        Operator o1 = new Operator("admin", "11111");
        operatorDAO.create(o1);
        o1.setUsername("camelo");
        operatorDAO.update(o1);
        assertEquals(operatorDAO.getByPK("camelo").getId(), o1.getId());
    }

    @Test
    void generateId() {
        OperatorFileImpl operatorDAO = new OperatorFileImpl();
        int a = Integer.parseInt(operatorDAO.generateId());
        int b = Integer.parseInt(operatorDAO.generateId());
        assertTrue(b > a);
    }

    @Test
    void deleteByPK() {
        OperatorFileImpl operatorDAO = new OperatorFileImpl();
        Operator o1 = new Operator("admin", "11111");
        Operator o2 = new Operator("admin22", "2222");
        operatorDAO.create(o1);
        operatorDAO.create(o2);
        o2.setUsername("admao");
        operatorDAO.update(o2);
        assertNotNull(operatorDAO.getByPK("admao"));
    }
}