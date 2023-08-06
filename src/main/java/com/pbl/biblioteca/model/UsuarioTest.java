package com.pbl.biblioteca.model;

public class UsuarioTest {
    public static void main(String[] args){
        Usuario test = new Usuario();
        test.setEndereco("Rua PAPAPSQ");
        test.setNome("Pedro");
        test.setSenha("1252");
        test.setTelefone("784989");
        test.setUsername("unfair");

        System.out.println("Id: " + test.getId());

        Usuario test2 = new Usuario();
        System.out.println("Novo Id: " + test2.getId());
    }
}
