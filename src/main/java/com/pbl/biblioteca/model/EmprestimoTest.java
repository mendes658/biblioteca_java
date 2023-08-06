package com.pbl.biblioteca.model;

import java.time.LocalDate;

public class EmprestimoTest {
    public static void main(String[] args){
        Emprestimo test = new Emprestimo();
        test.setIdLivro(12);
        test.setIdUser(1);

        LocalDate data = LocalDate.now();
        test.setDataInicio(data, 7);

        System.out.println("Inicio Emp: " + test.getDataInicio());
        System.out.println("Fim Emp: " + test.getDataFim());
        System.out.println("Id: " + test.getId());
    }
}
