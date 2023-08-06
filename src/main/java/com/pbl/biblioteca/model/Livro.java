package com.pbl.biblioteca.model;

import java.util.ArrayList;

public class Livro {

    private Integer id;
    private String titulo;
    private String autor;
    private String editora;
    private Integer ano;
    private String categoria;
    private String isbn;
    private static Integer exemplaresDisponiveis;
    private static Integer exemplaresTotais;
    private static final ArrayList<Integer> idEmprestimos = new ArrayList<>();


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

    public static Integer getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public static Integer getExemplaresTotais() {
        return exemplaresTotais;
    }

    public static ArrayList<Integer> getIdEmprestimos() {
        return idEmprestimos;
    }


    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

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
        exemplaresTotais = total;
    }


    // Controle de empréstimos
    public void addEmprestimo(Integer id){
        idEmprestimos.add(id);
        exemplaresDisponiveis --;
    }

    public void removeEmprestimo(Integer id){
        for (int i = 0; i < idEmprestimos.size(); i++) {
            if (idEmprestimos.get(i).equals(id)) {
                idEmprestimos.remove(i);
                exemplaresDisponiveis++;
                break;
            }
        }
    }

}
