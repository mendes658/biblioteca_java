package com.pbl.biblioteca.dao.BookReserve;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.BookReserve;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class BookReserveFileImpl extends ConnectionFile implements BookReserveDAO{

    @Override
    public boolean create(BookReserve bookReserveObj) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);

        bookReserveHM.put(bookReserveObj.getId(),bookReserveObj);

        return saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    @Override //trocar o isbn por pk em todas aí, padroniza essa porra
    public boolean deleteByPK(String id) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        bookReserveHM.remove(id);

        return saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    @Override
    public boolean update(BookReserve bookReserveObj) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        bookReserveHM.put(bookReserveObj.getId(), bookReserveObj);

        return saveAnyObject(bookReserveHM, bookReserveUrl);
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
        return ConnectionFile.generateId(bookReserveUrl);
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

        allFromBook.sort(Comparator.comparingInt(BookReserve::epochDateSetReserve).reversed());

        return allFromBook;
    }

    @Override
    public boolean removeAllFromUser(String username) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);

        boolean deleted = false;

        for (String key : bookReserveHM.keySet()){
            if (bookReserveHM.get(key).getUsername().equals(username)){
                bookReserveHM.remove(key);
            }
        }

        saveAnyObject(bookReserveHM, bookReserveUrl);
        return deleted;
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
            nowArray.sort(Comparator.comparingInt(BookReserve::epochDateSetReserve).reversed());
            allByBook.put(key, nowArray);
        }

        return allByBook;
    }
}
