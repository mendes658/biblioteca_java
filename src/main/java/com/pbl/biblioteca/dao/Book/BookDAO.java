package com.pbl.biblioteca.dao.Book;

import com.pbl.biblioteca.dao.crudDAO;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookDAO<Book> extends crudDAO<Book> {
    ArrayList<Book> getAllBooksFromCategory(String category);
    String generateId();
}
