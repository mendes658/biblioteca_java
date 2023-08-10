package com.pbl.biblioteca.model;
import java.io.Serializable;

public class Operator implements Serializable {
    private static Integer currentId = 0;

    public Operator(){
        currentId++;
        id = currentId.toString();
    }

    private String username;
    private final String id;
    private String role;
    private String password;


    // Getters
    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }


    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean setRole(String newRole) {
        if (newRole.equals("admin") || newRole.equals("biblio")){
            this.role = newRole;
            return true;
        }
        return false;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

