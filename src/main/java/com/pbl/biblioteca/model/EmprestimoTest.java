package com.pbl.biblioteca.model;

import java.time.LocalDate;

public class EmprestimoTest {
    public static void main(String[] args){
        Loan test = new Loan();
        test.setBookId(12);
        test.setUserId(1);

        LocalDate data = LocalDate.now();
        test.setInitialDate(data, 7);

        System.out.println("Inicio Emp: " + test.getInitialDate());
        System.out.println("Fim Emp: " + test.getFinalDate());
        System.out.println("Id: " + test.getId());

        Loan x = new Loan();

        System.out.println("Id: " + x.getId());
    }
}
