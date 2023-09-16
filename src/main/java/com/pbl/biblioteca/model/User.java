package com.pbl.biblioteca.model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private final String id;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;

    }
}
