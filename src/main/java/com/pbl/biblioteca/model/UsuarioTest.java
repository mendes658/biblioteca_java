package com.pbl.biblioteca.model;

import java.time.LocalDate;

public class UsuarioTest {
    public static void main(String[] args){
        Usuario test = new Usuario();
        test.setEndereco("Rua PAPAPSQ");
        test.setNome("Pedro");
        test.setSenha("1252");
        test.setTelefone("784989");
        test.setUsername("unfair");

        LocalDate plus7 = LocalDate.now().plusDays(7);

        System.out.println("Fim Bloqueio: " + test.getFimBloqueio());

        System.out.println("Id: " + test.getId());
        test.blockUser(plus7);
        System.out.println("Blocked: " + test.getBloqueado());
        System.out.println("Fim Bloqueio: " + test.getFimBloqueio());
        test.unblockUser();
        System.out.println("Blocked: " + test.getBloqueado());
        System.out.println("Fim Bloqueio: " + test.getFimBloqueio());

        Usuario test2 = new Usuario();
        System.out.println("Novo Id: " + test2.getId());
    }
}
