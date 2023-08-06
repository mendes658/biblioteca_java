package com.pbl.biblioteca.model;

import java.util.ArrayList;

public class Usuario {
    private static Integer currentId = 0;

    public Usuario(){
        currentId++;
        id = currentId;
    }

    private String username; // primary key
    private final ArrayList<Integer> emprestimosList = new ArrayList<>();
    private String nome;
    private String endereco;
    private String telefone;
    private final Integer id;
    private String senha;


    // Getters
    public static Integer getCurrentId() {
        return currentId;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Integer> getEmprestimosList() {
        return emprestimosList;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public Integer getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }


    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
