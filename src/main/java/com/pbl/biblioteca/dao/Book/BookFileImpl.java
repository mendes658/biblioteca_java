package com.pbl.biblioteca.dao.Book;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.HashMap;

public class BookFileImpl extends ConnectionFile implements BookDAO{

    @Override
    public void create(Book bookObject) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        bookHM.put(bookObject.getIsbn(), bookObject);

        saveAnyObject(bookHM, bookFileUrl);
    }

    @Override
    public Book getByPK(String isbn) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);

        return bookHM.get(isbn);
    }

    @Override
    public void update(Book bookObj) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        String isbn = bookObj.getIsbn();
        bookHM.put(isbn, bookObj);
        saveAnyObject(bookHM, bookFileUrl);

    }

    @Override
    public void deleteByPK(String isbn) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        if (bookHM.containsKey(isbn)) {
            bookHM.remove(isbn);
            saveAnyObject(bookHM, bookFileUrl);
        }
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
        return generateId(bookFileUrl);
    }

    @Override
    public ArrayList<Book> searchByTitle(String title){
        ArrayList<Book> matches = new ArrayList<>();
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        Book nowBook;
        String nowTitle;
        title = title.toLowerCase();

        for (String key : bookHM.keySet()){
            nowBook = bookHM.get(key);
            nowTitle = nowBook.getTitle().toLowerCase();

            if(nowTitle.equals(title)){ // Acerto exato sempre ficará em primeiro
                if (matches.isEmpty()){
                    matches.add(nowBook);
                } else {
                    matches.add(matches.get(0));
                    matches.set(0, nowBook);
                }
            } else if (nowTitle.matches(".*" + title + ".*")){
                matches.add(nowBook);
            }
        }

        return matches;
    }
}

