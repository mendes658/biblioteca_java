package com.pbl.biblioteca.dao.BookCopy;

import com.pbl.biblioteca.dao.CRUD;

public interface BookCopyDAO<BookCopy> extends CRUD<BookCopy> {
    public String generateId();
}
