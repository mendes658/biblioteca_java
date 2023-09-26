package com.pbl.biblioteca.dao;

import java.util.HashMap;

public interface CRUD<T>{

    void update(T obj);
    void deleteByPK(String pk);
    void create(T obj);
    T getByPK(String pk);
    HashMap<String, T> getAll();

}
