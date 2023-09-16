package com.pbl.biblioteca.dao.Operator;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.Operator;

public interface OperatorDAO extends CRUD<Operator> {
    String generateId();
}
