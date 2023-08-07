package com.pbl.biblioteca.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Livro implements Serializable {

    private static Integer currentId = 0;

    public Livro(){
        currentId++;
        id = currentId;
    }

    private final Integer id; // primary key
    private String titulo;
    private String autor;
    private String editora;
    private Integer ano;
    private String categoria;
    private String isbn; // unique
    private Integer exemplaresDisponiveis;
    private Integer exemplaresTotais;
    private final ArrayList<Integer> idEmprestimos = new ArrayList<>();


    // Getters
    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }


    public String getEditora() {
        return editora;
    }

    public Integer getAno() {
        return ano;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public Integer getExemplaresTotais() {
        return exemplaresTotais;
    }

    public ArrayList<Integer> getIdEmprestimos() {
        return idEmprestimos;
    }


    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setExemplaresTotais(Integer total) {
        this.exemplaresTotais = total;
        this.exemplaresDisponiveis = total;
    }


    // Controle de empréstimos
    public void addEmprestimo(Integer idEmp){
        this.idEmprestimos.add(idEmp);
        this.exemplaresDisponiveis --;
    }

    public void removeEmprestimo(Integer idEmp){
        for (int i = 0; i < this.idEmprestimos.size(); i++) {
            if (this.idEmprestimos.get(i).equals(idEmp)) {
                this.idEmprestimos.remove(i);
                this.exemplaresDisponiveis++;
                break;
            }
        }
    }

}


