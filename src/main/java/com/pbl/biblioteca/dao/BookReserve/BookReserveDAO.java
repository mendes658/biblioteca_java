package com.pbl.biblioteca.dao.BookReserve;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.BookReserve;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookReserveDAO extends CRUD<BookReserve> {
    String generateId();
    boolean removeAllFromUser(String username);
    ArrayList<BookReserve> getReservesFromBook(String bookIsbn);
    ArrayList<BookReserve> getAllFromReader(String username);

    HashMap<String, ArrayList<BookReserve>> getAllByBook();

}
