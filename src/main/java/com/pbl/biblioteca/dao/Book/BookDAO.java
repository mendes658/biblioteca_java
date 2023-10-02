package com.pbl.biblioteca.dao.Book;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.Book;

import java.util.ArrayList;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public interface BookDAO extends CRUD<Book> {

    /**
     * Pega todos os livros de uma determinada categoria
     * @param  category A categoria dos livros
     * @return Retorna um array com todos os livros
     */
    ArrayList<Book> getAllBooksFromCategory(String category);

    /**
     * Gera um novo id para o livro
     * @return Retorna o id em uma String
     */
    String generateId();

    /**
     * Busca por livros que possuam um título parecido com o param
     * @param  title O título do livro
     * @return Retorna um array com todos os matches
     */
    ArrayList<Book> searchByTitle(String title);
}
