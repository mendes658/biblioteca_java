package com.pbl.biblioteca.model;
import java.io.Serializable;

public class Operator implements Serializable {
    private static Integer currentId = 0;
    private String username;
    private final String id;
    private String password;


    public Operator(String newUsername, String newPassword){
        currentId++;
        id = currentId.toString();
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

