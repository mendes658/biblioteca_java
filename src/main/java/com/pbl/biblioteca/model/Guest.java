package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class Guest implements Serializable {

    /**
     * Busca por livros que possuam um título parecido com o param
     * @param  title O título do livro
     * @return Retorna um array com todos os matches
     */
    public ArrayList<Book> searchBookByTitle(String title){
        return DAO.getBookDAO().searchByTitle(title);
    }

    /**
     * Busca por livros que possuam um isbn parecido com o param
     * @param  isbn O isbn do livro
     * @return Retorna um array com todos os matches
     */
    public ArrayList<Book> searchBookByIsbn(String isbn){
        return DAO.getBookDAO().searchByIsbn(isbn);
    }

    /**
     * Busca por livros que possuam um autor parecido com o param
     * @param  author O autor do livro
     * @return Retorna um array com todos os matches
     */
    public ArrayList<Book> searchBookByAuthor(String author){
        return DAO.getBookDAO().searchByAuthor(author);
    }

    /**
     * Busca por livros que possuam uma categoria parecido com o param
     * @param  category A categoria do livro
     * @return Retorna um array com todos os matches
     */
    public ArrayList<Book> searchBookByCategory(String category){
        return DAO.bookDAO.searchByCategory(category);
    }
}
