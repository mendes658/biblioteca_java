package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;

import java.io.Serializable;
import java.util.ArrayList;

public class Guest implements Serializable {

    /**
     * Busca por livros que possuam um título parecido com o param
     * @param  title O título do livro
     * @return Retorna um array com todos os matches
     */
    public ArrayList<Book> searchBookByTitle(String title){
        return DAO.getBookDAO().searchByTitle(title);
    }

}
