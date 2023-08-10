package com.pbl.biblioteca.model;

import java.time.LocalDate;

public class UsuarioTest {
    public static void main(String[] args){
        User test = new User();
        test.setAdress("Rua PAPAPSQ");
        test.setName("Pedro");
        test.setPassword("1252");
        test.setTelephone("784989");
        test.setNickname("unfair");

        LocalDate plus7 = LocalDate.now().plusDays(7);

        System.out.println("Fim Bloqueio: " + test.getDateEndBlock());

        System.out.println("Id: " + test.getId());
        test.blockUser(plus7);
        System.out.println("Blocked: " + test.getBlocked());
        System.out.println("Fim Bloqueio: " + test.getDateEndBlock());
        test.unblockUser();
        System.out.println("Blocked: " + test.getBlocked());
        System.out.println("Fim Bloqueio: " + test.getDateEndBlock());

        User test2 = new User();
        System.out.println("Novo Id: " + test2.getId());
    }
}
