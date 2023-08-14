package com.pbl.biblioteca.dao;

import java.util.HashMap;

public interface crudDAO<T>{

    public boolean update(T obj);
    public boolean deleteByPK(String pk);
    public boolean create(T obj);
    public T getByPK(String pk);
    public HashMap<String, T> getAll();

}
