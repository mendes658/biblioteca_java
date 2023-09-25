package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class System implements Serializable {

    public void updateReservesAndUserStatus(){
        HashMap<String, BookReserve> allReserves = DAO.getBookReserveDAO().getAll();
        Book nowBook;
        BookReserve nowReserve;

        for (String key : allReserves.keySet()){
            nowReserve = allReserves.get(key);
            nowBook = DAO.getBookDAO().getByPK(nowReserve.getBookIsbn());

            if (nowBook.getAvailableCopies()){
        }
    }
}
