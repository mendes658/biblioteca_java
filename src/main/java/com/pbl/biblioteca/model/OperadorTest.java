package com.pbl.biblioteca.model;

public class OperadorTest {
    public static void main(String[] args){
        Operator teste = new Operator();
        teste.setRole("qdq");
        System.out.println(teste.getRole());

        teste.setRole("admin");
        System.out.println(teste.getRole());

        teste.setRole("biblio");
        System.out.println(teste.getRole());
    }
}
