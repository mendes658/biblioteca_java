package com.pbl.biblioteca.model;
import com.pbl.biblioteca.dao.Book.BookDAOImpl;
import com.pbl.biblioteca.dao.Operator.OperatorDAOImpl;

import java.io.Serializable;

public class Operator implements Serializable {
    private String username;
    private final String id;
    private String password;


    public Operator(String newUsername, String newPassword){
        OperatorDAOImpl operatorDAO = new OperatorDAOImpl();
        this.id = operatorDAO.generateId();
        this.username = newUsername;
        this.password = newPassword;
    }



    // Getters
    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }


    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

