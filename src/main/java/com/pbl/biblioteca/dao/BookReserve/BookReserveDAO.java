package com.pbl.biblioteca.dao.BookReserve;

import com.pbl.biblioteca.dao.crudDAO;
import com.pbl.biblioteca.model.BookReserve;

import java.util.ArrayList;

public interface BookReserveDAO<BookReserve> extends crudDAO<BookReserve> {
    String generateId();
    boolean removeAllFromUser(String username);
    ArrayList<BookReserve> getReservesFromBook(String bookIsbn);
    ArrayList<BookReserve> getAllFromUser(String username);
    boolean removeReserve(String bookIsbn, String username);

}
