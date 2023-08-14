package com.pbl.biblioteca.dao.Operator;

import com.pbl.biblioteca.dao.crudDAO;

public interface OperatorDAO<Operator> extends crudDAO<Operator> {
    public String generateId();
}
