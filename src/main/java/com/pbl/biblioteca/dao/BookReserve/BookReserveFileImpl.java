package com.pbl.biblioteca.dao.BookReserve;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.BookReserve;

import java.util.ArrayList;
import java.util.HashMap;

public class BookReserveFileImpl extends ConnectionFile implements BookReserveDAO{

    @Override
    public boolean create(BookReserve bookReserveObj) {
        HashMap<String, ArrayList<BookReserve>> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<BookReserve> reserveArray = bookReserveHM.get(bookReserveObj.getBookIsbn());

        if (reserveArray == null){
            reserveArray = new ArrayList<>();
        }

        reserveArray.add(bookReserveObj);

        bookReserveHM.put(bookReserveObj.getBookIsbn(),reserveArray);

        return saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    @Override
    public boolean deleteByPK(String isbn) {
        HashMap<String, ArrayList<BookReserve>> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        bookReserveHM.remove(isbn);

        return saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    @Override
    public boolean update(BookReserve bookReserveObj) {
        HashMap<String, ArrayList<BookReserve>> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<BookReserve> mainArray = bookReserveHM.get(bookReserveObj.getBookIsbn());
        String nowId = bookReserveObj.getId();

        for(int i = 0; i<mainArray.size(); i++){
            if (mainArray.get(i).getId().equals(nowId)){
                mainArray.set(i, bookReserveObj);
                bookReserveHM.put(bookReserveObj.getBookIsbn(), mainArray);
                return saveAnyObject(bookReserveHM, bookReserveUrl);
            }
        }

        return false;
    }

    @Override
    public BookReserve getByPK(String id) {
        HashMap<String, ArrayList<BookReserve>> bookReserveHM = getAnySavedHashmap(bookReserveUrl);

        for (String key : bookReserveHM.keySet()){
            for(BookReserve reserve : bookReserveHM.get(key)){
                if (reserve.getId().equals(id)){
                    return reserve;
                }
            }
        }

        return null;
    }

    @Override
    public HashMap<String, BookReserve> getAll() {
        HashMap<String, BookReserve> all = new HashMap<>();
        HashMap<String, ArrayList<BookReserve>> bookReserveHM = getAnySavedHashmap(bookReserveUrl);

        for (String key : bookReserveHM.keySet()){
            for(BookReserve reserve : bookReserveHM.get(key)){
                all.put(reserve.getId(), reserve);
            }
        }

        return all;
    }

    @Override
    public String generateId() {
        return ConnectionFile.generateId(bookReserveUrl);
    }

    @Override
    public ArrayList<BookReserve> getReservesFromBook(String bookIsbn) {
        HashMap<String, ArrayList<BookReserve>> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        return bookReserveHM.get(bookIsbn);
    }

    @Override
    public boolean removeAllFromUser(String username) {
        HashMap<String, ArrayList<BookReserve>> bookReserveHM = getAnySavedHashmap(bookReserveUrl);

        boolean deleted = false;

        for (String key : bookReserveHM.keySet()){

            int i = 0;
            while (bookReserveHM.get(key).size() > i){
                if (bookReserveHM.get(key).get(i).getUsername().equals(username)){
                    deleted = true;
                    bookReserveHM.get(key).remove(i);
                }
                i++;
            }

        }

        saveAnyObject(bookReserveHM, bookReserveUrl);
        return deleted;
    }

    @Override
    public boolean removeReserve(String bookIsbn, String username) {
        HashMap<String, ArrayList<BookReserve>> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<BookReserve> bookReserveArray = bookReserveHM.get(bookIsbn);

        boolean deleted = false;
        int size = bookReserveArray.size();
        for(int i = 0; i<size; i++){
            if (bookReserveArray.get(i).getUsername().equals(username)){
                bookReserveArray.remove(i);
                deleted = true;
                break;
            }
        }

        return deleted;
    }

    @Override
    public ArrayList<BookReserve> getAllFromUser(String username) {
        HashMap<String, ArrayList<BookReserve>> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<BookReserve> allFromUser = new ArrayList<>();


        for (String key : bookReserveHM.keySet()){
            int i = 0;
            while (bookReserveHM.get(key).size() > i){
                if (bookReserveHM.get(key).get(i).getUsername().equals(username)){
                    allFromUser.add((bookReserveHM).get(key).get(i));
                }
                i++;
            }
        }

        return allFromUser;
    }
}
