package com.pbl.biblioteca.dao.Reader;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.Reader;

public interface ReaderDAO extends CRUD<Reader> {

    String generateId();
}
