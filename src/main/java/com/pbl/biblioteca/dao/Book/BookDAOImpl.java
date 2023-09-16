package com.pbl.biblioteca.dao.Book;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.BookCopy;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BookDAOImpl extends ConnectionDAO implements BookDAO{

    @Override
    public boolean create(Book bookObject) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        bookHM.put(bookObject.getIsbn(), bookObject);

        return saveAnyObject(bookHM, bookFileUrl);
    }

    @Override
    public Book getByPK(String isbn) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);

        return bookHM.get(isbn);
    }

    @Override
    public boolean update(Book bookObj) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        String isbn = bookObj.getIsbn();
        bookHM.put(isbn, bookObj);
        saveAnyObject(bookHM, bookFileUrl);

        return true;
    }

    @Override
    public boolean deleteByPK(String isbn) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        if (!bookHM.containsKey(isbn)) {
            return false;
        } else {
            Book toDelete = bookHM.get(isbn);
            bookHM.remove(isbn);
            saveAnyObject(bookHM, bookFileUrl);
        }

        return true;
    }

    @Override
    public HashMap<String, Book> getAll() {
        return getAnySavedHashmap(bookFileUrl);
    }

    @Override
    public ArrayList<Book> getAllBooksFromCategory(String category) {

        ArrayList<Book> booksFromCategory = new ArrayList<>();
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        Book nowBook = null;

        for (String isbn : bookHM.keySet()) {
            nowBook = bookHM.get(isbn);
            if (nowBook.getCategory().equals(category)) {
                booksFromCategory.add(nowBook);
            }
        }

        return booksFromCategory;
    }

    @Override
    public String generateId() {
        return ConnectionDAO.generateId(bookFileUrl);
    }
}

