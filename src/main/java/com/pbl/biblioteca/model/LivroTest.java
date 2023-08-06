package com.pbl.biblioteca.model;

public class LivroTest {
    public static void main(String[] args) {
        Livro teste = new Livro();
        teste.setAno(2002);
        teste.setAutor("Pedro");
        teste.setTitulo("Dom Casmurro");
        teste.setExemplaresTotais(5);

        System.out.println("Livro: " + teste.getTitulo());
        System.out.println("Livro Id: " + teste.getId());
        System.out.println("Disponivel: " + teste.getExemplaresDisponiveis());

        teste.addEmprestimo(12);
        teste.addEmprestimo(13);
        teste.addEmprestimo(14);

        System.out.println("Totais: " + teste.getExemplaresTotais());
        System.out.println("Disponivel: " + teste.getExemplaresDisponiveis());

        System.out.println("Ids: " + teste.getIdEmprestimos());

        teste.removeEmprestimo(13);

        System.out.println("Ids: " + teste.getIdEmprestimos());
        System.out.println("Disponivel: " + teste.getExemplaresDisponiveis());

        Livro teste2 = new Livro();
        teste2.setTitulo("Macarrao");

        System.out.println("Livro: " + teste2.getTitulo());
        System.out.println("Livro Id: " + teste2.getId());


    }
}

