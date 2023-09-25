package com.pbl.biblioteca.dao.Book;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.Book;

import java.util.ArrayList;

public interface BookDAO extends CRUD<Book> {
    ArrayList<Book> getAllBooksFromCategory(String category);
    String generateId();

    ArrayList<Book> searchByTitle(String title);
}
