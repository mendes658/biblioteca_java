package com.pbl.biblioteca.dao.Book;

import com.pbl.biblioteca.dao.crudDAO;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookDAO<Book> extends crudDAO<Book> {
    public HashMap<String, ArrayList<String>> getAllBooksByCategory();
    public ArrayList<String> getAllBooksFromCategory(String category);

    public String generateId();
}
