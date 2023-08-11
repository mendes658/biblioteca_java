package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.Book;

import java.util.HashMap;

public class BookDAO extends ConnectionDAO{

    public static boolean saveBook(Book bookObject){
        HashMap<String, Book> bookHM = getBookHashmap();
        if (bookHM == null){
            bookHM = new HashMap<>();
        }

        bookHM.put(bookObject.getIsbn(),bookObject);

        return saveAnyHashmap(bookHM, bookFileUrl);
    }

}
