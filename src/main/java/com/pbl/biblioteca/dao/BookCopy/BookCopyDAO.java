package com.pbl.biblioteca.dao.BookCopy;

import com.pbl.biblioteca.dao.crudDAO;

public interface BookCopyDAO<BookCopy> extends crudDAO<BookCopy> {
    public String generateId();
}
