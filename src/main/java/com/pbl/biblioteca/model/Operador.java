package com.pbl.biblioteca.model;

public class Operador {
    private static Integer currentId = 0;

    public Operador(){
        currentId++;
        id = currentId;
    }

    private String username;
    private final Integer id;
    private String cargo;
    private String senha;


    // Getters
    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public String getSenha() {
        return senha;
    }


    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}

