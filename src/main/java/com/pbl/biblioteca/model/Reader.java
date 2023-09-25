package com.pbl.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;

public class Reader extends User implements Serializable {

    private Boolean blocked;
    private LocalDate dateEndBlock;

    public Reader(String username, String name,
                  String address, String telephone, String password){

        super(username, password, address, telephone, name, "reader");
        this.blocked = false;
        this.dateEndBlock = null;

    }


    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public void setDateEndBlock(LocalDate dateEndBlock) {
        this.dateEndBlock = dateEndBlock;
    }

    public Boolean getBlocked(){
        return blocked;
    }

    public LocalDate getDateEndBlock(){
        return dateEndBlock;
    }

    public void createBookReserve(Book book) throws readerIsBlockedException, fullException,
            copyAvailableException{
        if (this.blocked){
            throw new readerIsBlockedException("Reader is blocked");
        }

        ArrayList<BookReserve> reserves = DAO.getBookReserveDAO().getReservesFromBook(book.getIsbn());

        if (reserves.size() > 3){
            throw new fullException("Too many reserves to current book");
        }

        if (book.getAvailableCopies() > 0){
            throw new copyAvailableException("There are copies available to borrow, can't reserve");
        }

        if (DAO.getBookReserveDAO().getAllFromReader(this.getUsername()).size() > 2){
            throw new fullException("Too many active reserves from current user");
        }

        BookReserve reserve = new BookReserve(this.getUsername(), book.getIsbn());
        DAO.getBookReserveDAO().create(reserve);

    }

    public void removeReserve(Book book) throws notFoundException{
        ArrayList<BookReserve> reserves = DAO.getBookReserveDAO().getAllFromReader(this.getUsername());

        for (BookReserve r : reserves){
            if (r.getBookIsbn().equals(book.getIsbn())){
                DAO.getBookReserveDAO().deleteByPK(r.getId());
                return;
            }
        }

        throw new notFoundException("No reserve found to current book");

    }

    public ArrayList<Book> searchBookByTitle(String title){
        return DAO.getBookDAO().searchByTitle(title);
    }

}
