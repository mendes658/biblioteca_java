package com.pbl.biblioteca.model;

public class LivroTest {
    public static void main(String[] args) {
        Book teste = new Book();
        teste.setYear(2002);
        teste.setAuthor("Pedro");
        teste.setTitle("Dom Casmurro");
        teste.setTotalCopies(5);

        System.out.println("Livro: " + teste.getTitle());
        System.out.println("Livro Id: " + teste.getId());
        System.out.println("Disponivel: " + teste.getAvailableCopies());

        teste.addLoan(12);
        teste.addLoan(13);
        teste.addLoan(14);

        System.out.println("Totais: " + teste.getTotalCopies());
        System.out.println("Disponivel: " + teste.getAvailableCopies());

        System.out.println("Ids: " + teste.getLoanIds());

        teste.removeLoan(13);

        System.out.println("Ids: " + teste.getLoanIds());
        System.out.println("Disponivel: " + teste.getAvailableCopies());

        Book teste2 = new Book();
        teste2.setTitle("Macarrao");

        System.out.println("Livro: " + teste2.getTitle());
        System.out.println("Livro Id: " + teste2.getId());


    }
}

