package com.pbl.biblioteca.dao.Operator;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Operator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OperatorDAOImplTest {

    @BeforeEach
    void setUp() {
        ConnectionDAO.setTestFileUrls();
    }

    @AfterEach
    void tearDown() {
        ConnectionDAO.setDefaultFileUrls();
    }

    @Test
    void create() {
        OperatorDAOImpl operatorDAO = new OperatorDAOImpl();
        Operator o1 = new Operator("admin", "11111");
        operatorDAO.create(o1);
        assertEquals(operatorDAO.getByPK("admin").getUsername(), "admin");
    }

    @Test
    void getByPK() {
        OperatorDAOImpl operatorDAO = new OperatorDAOImpl();
        Operator o1 = new Operator("admin", "11111");
        operatorDAO.create(o1);
        assertEquals(operatorDAO.getByPK("admin").getId(), o1.getId());
    }

    @Test
    void getAll() {
        OperatorDAOImpl operatorDAO = new OperatorDAOImpl();
        Operator o1 = new Operator("admin", "11111");
        operatorDAO.create(o1);
        HashMap<String, Operator> all = operatorDAO.getAll();
        assertEquals(all.get("admin").getId(), o1.getId());
    }

    @Test
    void update() {
        OperatorDAOImpl operatorDAO = new OperatorDAOImpl();
        Operator o1 = new Operator("admin", "11111");
        operatorDAO.create(o1);
        o1.setUsername("camelo");
        operatorDAO.update(o1);
        assertEquals(operatorDAO.getByPK("camelo").getId(), o1.getId());
    }

    @Test
    void generateId() {
        OperatorDAOImpl operatorDAO = new OperatorDAOImpl();
        int a = Integer.parseInt(operatorDAO.generateId());
        int b = Integer.parseInt(operatorDAO.generateId());
        assertTrue(b > a);
    }

    @Test
    void deleteByPK() {
        OperatorDAOImpl operatorDAO = new OperatorDAOImpl();
        Operator o1 = new Operator("admin", "11111");
        Operator o2 = new Operator("admin22", "2222");
        operatorDAO.create(o1);
        operatorDAO.create(o2);
        o2.setUsername("admao");
        operatorDAO.update(o2);
        assertNotNull(operatorDAO.getByPK("admao"));
    }
}