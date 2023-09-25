package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class System implements Serializable {

    //opaopoapoap
    public void updateReservesAndUserStatus(){
        HashMap<String, ArrayList<BookReserve>> allReserves = DAO.getBookReserveDAO().getAllByBook();
        Book nowBook;
        BookReserve nowReserve;
    }
}
