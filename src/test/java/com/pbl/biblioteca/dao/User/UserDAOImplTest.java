package com.pbl.biblioteca.dao.User;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.dao.Operator.OperatorDAOImpl;
import com.pbl.biblioteca.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {

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
        UserDAOImpl userDAO = new UserDAOImpl();
        User u1 = new User("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        userDAO.create(u1);
        assertEquals(userDAO.getByPK("pedromendes").getName(), "Pedro Mendes");
    }

    @Test
    void getByPK() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User u1 = new User("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        userDAO.create(u1);
        assertEquals(userDAO.getAll().get("pedromendes").getName(), "Pedro Mendes");
    }

    @Test
    void update() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User u1 = new User("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        userDAO.create(u1);
        u1.setName("Jacaré");
        userDAO.update(u1);
        assertEquals(userDAO.getByPK("pedromendes").getName(), "Jacaré");
    }

    @Test
    void deleteByPK() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User u1 = new User("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        userDAO.create(u1);
        userDAO.deleteByPK("pedromendes");
        assertNull(userDAO.getByPK("pedromendes"));
    }

    @Test
    void generateId() {
        UserDAOImpl userDAO = new UserDAOImpl();
        int a = Integer.parseInt(userDAO.generateId());
        int b = Integer.parseInt(userDAO.generateId());
        assertTrue(b > a);
    }

    @Test
    void getAll() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User u1 = new User("pedromendes", "Pedro Mendes", "te tete",
                "79585959", "12345");
        userDAO.create(u1);
        assertEquals(userDAO.getAll().get("pedromendes").getName(), "Pedro Mendes");
    }
}