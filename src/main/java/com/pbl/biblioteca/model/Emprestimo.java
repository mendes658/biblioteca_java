package com.pbl.biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {
    private static Integer currentId = 0;

    public Emprestimo(){
        currentId++;
        id = currentId;
    }

    private final Integer id;
    private Integer idLivro;
    private Integer idUser;
    private LocalDate dataInicio;
    private LocalDate dataFim;


    // Getters
    public Integer getId() {
        return id;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }


    // Setters
    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setDataInicio(LocalDate dataInicio, Integer dias) {
        this.dataInicio = dataInicio;
        this.dataFim = dataInicio.plusDays(dias);
    }

}
