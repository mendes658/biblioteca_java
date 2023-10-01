package com.pbl.biblioteca.dao.BookReserve;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.BookReserve;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookReserveDAO extends CRUD<BookReserve> {

    /**
     * Gera um novo id para a reserva
     * @return Retorna o id numa String
     */
    String generateId();

    /**
     * Deleta todas as reservas de um Reader
     * @param  username A primary key do Reader
     */
    void removeAllFromReader(String username);

    /**
     * Busca por todas as reservas de um livro
     * @param  bookIsbn O isbn do livro
     * @return Retorna um array com todas as reservas
     */
    ArrayList<BookReserve> getReservesFromBook(String bookIsbn);

    /**
     * Busca por todas as reservas de um Reader
     * @param  username A primary key do Reader
     * @return Retorna um array com todas as reservas
     */
    ArrayList<BookReserve> getAllFromReader(String username);

    /**
     * Pega o total de reservas ativas
     * @return Retorna um Inteiro com o total de reservas
     */
    Integer getTotalReserves();

    /**
     * Pega todas as reservas, organizadas livro a livro
     * @return Retorna um HashMap, a chave é o isbn do livro,
     * o valor é um Array com todas as reservas daquele livro
     */
    HashMap<String, ArrayList<BookReserve>> getAllByBook();

    /**
     * Busca por todas as reservas de um livro
     * @param  isbn A primary key do livro
     * @return Retorna um array com todas as reservas
     */
    ArrayList<BookReserve> getAllFromBook(String isbn);

}
