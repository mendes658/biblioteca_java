package com.pbl.biblioteca.model;

public class LivroTest {
    public static void main(String[] args) {
        Livro teste = new Livro();
        teste.setAno(2002);
        teste.setAutor("Pedro");
        Livro.setExemplaresTotais(5);

        System.out.println("Disponivel: " + Livro.getExemplaresDisponiveis());

        Livro.addEmprestimo(12);
        Livro.addEmprestimo(13);
        Livro.addEmprestimo(14);

        System.out.println("Totais: " + Livro.getExemplaresTotais());
        System.out.println("Disponivel-3: " + Livro.getExemplaresDisponiveis());

        System.out.println("Ids: " + Livro.getIdEmprestimos());

        Livro.removeEmprestimo(13);

        System.out.println("Ids: " + Livro.getIdEmprestimos());
        System.out.println("Disponivel: " + Livro.getExemplaresDisponiveis());
    }
}

