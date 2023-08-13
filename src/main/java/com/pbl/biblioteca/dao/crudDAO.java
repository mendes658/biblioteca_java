package com.pbl.biblioteca.dao;

public interface crudDAO<T>{

    public boolean update(T obj);
    public boolean deleteByPK(String pk);
    public boolean create(T obj);
    public T getByPK(String pk);
}
