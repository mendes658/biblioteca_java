package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.wrongPasswordException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class LocalSystem implements Serializable {

    public static void updateReserves(){
        HashMap<String, ArrayList<BookReserve>> allReserves = DAO.getBookReserveDAO().getAllByBook();
        Book nowBook;
        BookReserve nowReserve;
        ArrayList<BookReserve> nowArray;
        LocalDate nowEndReserveDate;
        Reader nowReader;
        int freeCopies; // copias liberadas, para que seja setada a data fim da reserva caso
                        // exista uma copia livre para o emprestimo

        for(String isbn : allReserves.keySet()){
            nowArray = allReserves.get(isbn);
            nowBook = DAO.getBookDAO().getByPK(isbn);
            freeCopies = nowBook.getAvailableCopies();

            for (int i = 0; i < nowArray.size(); i++){
                nowReserve = nowArray.get(i);
                nowEndReserveDate = nowReserve.getDateEndReserve();
                nowReader = DAO.getReaderDAO().getByPK(nowReserve.getUsername());

                if (i < freeCopies && nowEndReserveDate == null) {
                    nowReserve.setDateEndReserve(LocalDate.now().plusDays(2));
                    DAO.getBookReserveDAO().update(nowReserve);
                }

                if (nowReader.getBlocked()){
                    DAO.getBookReserveDAO().deleteByPK(nowReserve.getId());
                }

                if (nowEndReserveDate != null && nowEndReserveDate.isBefore(LocalDate.now())) {
                    DAO.getBookReserveDAO().deleteByPK(nowReserve.getId());
                }
            }
        }
    }
    /*
    public void updateReadersReserveStatus(){
        HashMap<String, ArrayList<BookReserve>> allReserves = DAO.getBookReserveDAO().getAllByBook();
        BookReserve nowReserve;
        ArrayList<BookReserve> nowArray;
        LocalDate nowEndReserveDate;
        Reader nowReader;

        for(String isbn : allReserves.keySet()){
            nowArray = allReserves.get(isbn);

            for (BookReserve bookReserve : nowArray) {
                nowReserve = bookReserve;
                nowEndReserveDate = nowReserve.getDateEndReserve();
                nowReader = DAO.getReaderDAO().getByPK(nowReserve.getUsername());

                if (nowEndReserveDate != null && nowEndReserveDate.isBefore(LocalDate.now())) {
                    nowReader.setBlocked(true);
                    nowReader.setDateEndBlock(LocalDate.now().plusDays(7));

                    DAO.getReaderDAO().update(nowReader);
                }

            }
        }
    } */


    public static void updateReadersBlockStatus(){
        HashMap<String, Reader> all = DAO.getReaderDAO().getAll();
        Reader nowReader;

        for (String id : all.keySet()){
            nowReader = all.get(id);

            if (nowReader.getBlocked().equals(true)
                    && nowReader.getDateEndBlock().isBefore(LocalDate.now())){

                nowReader.setBlocked(false);
                DAO.getReaderDAO().update(nowReader);
            }
        }
    }


    public static void updateSystem(){
        updateReadersBlockStatus();
        updateReserves();
    }

    public User login(String username, String password, String type) throws
            wrongPasswordException, notFoundException{

        return DAO.getUserDAO().login(username, password, type);
    }



}
