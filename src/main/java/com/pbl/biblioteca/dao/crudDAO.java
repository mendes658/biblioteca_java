package com.pbl.biblioteca.dao;

import java.util.HashMap;

public interface crudDAO<T>{

    boolean update(T obj);
    boolean deleteByPK(String pk);
    boolean create(T obj);
    T getByPK(String pk);
    HashMap<String, T> getAll();

}
