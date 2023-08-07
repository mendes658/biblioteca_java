package com.pbl.biblioteca.model;

public class OperadorTest {
    public static void main(String[] args){
        Operador teste = new Operador();
        teste.setCargo("qdq");
        System.out.println(teste.getCargo());

        teste.setCargo("admin");
        System.out.println(teste.getCargo());

        teste.setCargo("biblio");
        System.out.println(teste.getCargo());
    }
}
