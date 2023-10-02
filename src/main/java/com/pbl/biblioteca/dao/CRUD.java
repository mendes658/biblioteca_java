package com.pbl.biblioteca.dao;

import java.util.HashMap;

public interface CRUD<T>{


    /**
     * Atualiza um objeto salvo
     * @param  obj Objeto que será atualizado
     */
    void update(T obj);

    /**
     * Deleta um objeto salvo
     * @param pk Primary key
     */
    void deleteByPK(String pk);

    /**
     * Salva um objeto
     * @param  obj Objeto que será salvo
     */
    void create(T obj);

    /**
     * Pega um objeto salvo, através da primary key
     * @param  pk Primary key
     * @return Retorna o objeto
     */
    T getByPK(String pk);

    /**
     * Pega todos os objetos salvos atualmente
     * @return Retorna um Hashmap com todos os objetos
     */
    HashMap<String, T> getAll();

}
