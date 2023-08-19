package com.pbl.biblioteca.dao.Book;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Book;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BookDAOImpl extends ConnectionDAO implements BookDAO<Book> {

    @Override
    public boolean create(Book bookObject){
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        bookHM.put(bookObject.getIsbn(),bookObject);
        updateCategoryFile(bookObject);

        return saveAnyObject(bookHM, bookFileUrl);
    }

    @Override
    public Book getByPK(String isbn){
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);

        return bookHM.get(isbn);
    }

    @Override
    public boolean update(Book bookObj) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        String isbn = bookObj.getIsbn();
        bookHM.put(isbn, bookObj);

        updateCategoryFile(bookObj); // tem que ser chamada antes do salvamento

        saveAnyObject(bookHM, bookFileUrl);

        return true;
    }

    @Override
    public boolean deleteByPK(String isbn) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        if (!bookHM.containsKey(isbn)){
            return false;
        } else {
            Book toDelete = bookHM.get(isbn);
            deleteFromCategoryFile(toDelete);

            bookHM.remove(isbn);
            saveAnyObject(bookHM, bookFileUrl);
        }

        return true;
    }

    @Override
    public HashMap<String, ArrayList<String>> getAllBooksByCategory() {
        return getAnySavedHashmap(booksIsbnsByCategoryUrl);
    }

    @Override
    public HashMap<String, Book> getAll(){
        return getAnySavedHashmap(bookFileUrl);
    }

    @Override
    public ArrayList<String> getAllBooksFromCategory(String category) {
        HashMap<String, ArrayList<String>> booksByCatHM = getAnySavedHashmap(booksIsbnsByCategoryUrl);
        return booksByCatHM.get(category);
    }

    @Override
    public String generateId() {
        return ConnectionDAO.generateId(bookFileUrl);
    }


    private void updateCategoryFile(Book newBook){
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        HashMap<String, ArrayList<String>> bookByCatHM = getAnySavedHashmap(booksIsbnsByCategoryUrl);
        Book oldBook = bookHM.get(newBook.getIsbn());

        if (oldBook != null && !newBook.getCategory().equals(oldBook.getCategory())){
            ArrayList<String> oldList = bookByCatHM.get(oldBook.getCategory());
            oldList.removeIf(e -> e.equals(oldBook.getIsbn()));
            bookByCatHM.put(oldBook.getCategory(), oldList);

            ArrayList<String> newList =
                    bookByCatHM.computeIfAbsent(newBook.getCategory(), k -> new ArrayList<>());
            newList.add(newBook.getIsbn());
            bookByCatHM.put(newBook.getCategory(), newList);
        } else {
            ArrayList<String> newList =
                    bookByCatHM.computeIfAbsent(newBook.getCategory(), k -> new ArrayList<String>());

            newList.add(newBook.getIsbn());
            bookByCatHM.put(newBook.getCategory(),newList);
        }

        saveAnyObject(bookByCatHM, booksIsbnsByCategoryUrl);
    }

    private void deleteFromCategoryFile(Book bookObj){
        HashMap<String, ArrayList<String>> bookByCatHM = getAnySavedHashmap(booksIsbnsByCategoryUrl);
        ArrayList<String> allBooksSameCat =
                bookByCatHM.computeIfAbsent(bookObj.getCategory(), k -> new ArrayList<>());
        String isbn = bookObj.getIsbn();

        allBooksSameCat.removeIf(e -> e.equals(isbn));

        bookByCatHM.put(bookObj.getCategory(), allBooksSameCat);
        saveAnyObject(bookByCatHM, booksIsbnsByCategoryUrl);
    }
}
