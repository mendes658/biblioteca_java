package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.Book;

import java.util.HashMap;

public class BookDAO extends ConnectionDAO{

    protected static final String booksIdsByCategoryUrl = "books_ids_category.ser";

    public static boolean saveBook(Book bookObject){
        HashMap<String, Book> bookHM = getBookHashmap();

        bookHM.put(bookObject.getIsbn(),bookObject);

        return saveAnyHashmap(bookHM, bookFileUrl);
    }



}
