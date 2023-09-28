package com.pbl.biblioteca.dao.BookReserve;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.BookReserve;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class BookReserveFileImpl extends ConnectionFile implements BookReserveDAO{

    @Override
    public void create(BookReserve bookReserveObj) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);

        bookReserveHM.put(bookReserveObj.getId(),bookReserveObj);

        saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    @Override
    public void deleteByPK(String id) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        bookReserveHM.remove(id);

        saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    @Override
    public void update(BookReserve bookReserveObj) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        bookReserveHM.put(bookReserveObj.getId(), bookReserveObj);

        saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    @Override
    public BookReserve getByPK(String id) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);

        return bookReserveHM.get(id);
    }

    @Override
    public HashMap<String, BookReserve> getAll() {
        return  getAnySavedHashmap(bookReserveUrl);
    }

    @Override
    public String generateId() {
        return generateId(bookReserveUrl);
    }

    @Override
    public ArrayList<BookReserve> getReservesFromBook(String bookIsbn) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<BookReserve> allFromBook = new ArrayList<>();

        for (String key : bookReserveHM.keySet()){
            if(bookReserveHM.get(key).getBookIsbn().equals(bookIsbn)){
                allFromBook.add(bookReserveHM.get(key));
            }
        }

        allFromBook.sort(Comparator.comparingInt(BookReserve::epochDateSetReserve));

        return allFromBook;
    }

    @Override
    public void removeAllFromReader(String username) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<String> toRemove = new ArrayList<>();

        for (String key : bookReserveHM.keySet()){
            if (bookReserveHM.get(key).getUsername().equals(username)){
                toRemove.add(key);
            }
        }

        for (String key : toRemove){
            bookReserveHM.remove(key);
        }

        saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    @Override
    public ArrayList<BookReserve> getAllFromReader(String username) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<BookReserve> allFromUser = new ArrayList<>();

        for (String key : bookReserveHM.keySet()){
            if (bookReserveHM.get(key).getUsername().equals(username)){
                allFromUser.add(bookReserveHM.get(key));
            }
        }

        return allFromUser;
    }

    @Override
    public HashMap<String, ArrayList<BookReserve>> getAllByBook(){
        HashMap<String, ArrayList<BookReserve>> allByBook = new HashMap<>();
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        BookReserve nowReserve;
        ArrayList<BookReserve> nowArray;

        for (String key : bookReserveHM.keySet()){
            nowReserve = bookReserveHM.get(key);
            nowArray = allByBook.getOrDefault(nowReserve.getBookIsbn(), new ArrayList<>());

            nowArray.add(nowReserve);

            allByBook.put(nowReserve.getBookIsbn(), nowArray);
        }

        for (String key : allByBook.keySet()){
            nowArray = allByBook.get(key);
            nowArray.sort(Comparator.comparingInt(BookReserve::epochDateSetReserve));
            allByBook.put(key, nowArray);
        }

        return allByBook;
    }

    @Override
    public Integer getTotalReserves(){
        return getAll().size();
    }


    @Override
    public ArrayList<BookReserve> getAllFromBook(String isbn) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<BookReserve> allFromBook = new ArrayList<>();

        for (String key : bookReserveHM.keySet()){
            if (bookReserveHM.get(key).getBookIsbn().equals(isbn)){
                allFromBook.add(bookReserveHM.get(key));
            }
        }

        return allFromBook;
    }
}
