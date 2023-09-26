package com.pbl.biblioteca.dao.BookReserve;

import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.BookReserve;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class BookReserveMemoryImpl extends ConnectionMemory implements BookReserveDAO{

    @Override
    public void create(BookReserve bookReserveObj) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap("bookReserve");

        bookReserveHM.put(bookReserveObj.getId(),bookReserveObj);

        saveAnyObject(bookReserveHM, "bookReserve");
    }

    @Override
    public void deleteByPK(String id) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap("bookReserve");
        bookReserveHM.remove(id);

        saveAnyObject(bookReserveHM, "bookReserve");
    }

    @Override
    public void update(BookReserve bookReserveObj) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap("bookReserve");
        bookReserveHM.put(bookReserveObj.getId(), bookReserveObj);

        saveAnyObject(bookReserveHM, "bookReserve");
    }

    @Override
    public BookReserve getByPK(String id) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap("bookReserve");

        return bookReserveHM.get(id);
    }

    @Override
    public HashMap<String, BookReserve> getAll() {
        return  getAnySavedHashmap("bookReserve");
    }

    @Override
    public String generateId() {
        return generateId("bookReserve");
    }

    @Override
    public ArrayList<BookReserve> getReservesFromBook(String bookIsbn) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap("bookReserve");
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
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap("bookReserve");
        ArrayList<String> toRemove = new ArrayList<>();

        for (String key : bookReserveHM.keySet()){
            if (bookReserveHM.get(key).getUsername().equals(username)){
                toRemove.add(key);
            }
        }

        for (String key : toRemove){
            bookReserveHM.remove(key);
        }

        saveAnyObject(bookReserveHM, "bookReserve");
    }

    @Override
    public ArrayList<BookReserve> getAllFromReader(String username) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap("bookReserve");
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
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap("bookReserve");
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
}
