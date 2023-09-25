package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Librarian extends Operator{


    public Librarian(String username, String password, String address, String telephone,
                     String name){
        super(username, password, address, telephone, name, "librarian");

    }

    public Loan createBookLoan(Book book, Reader reader, Integer days) throws
            readerIsBlockedException, notFoundException, fullException,
            tooManyReservesException{

        if (reader.getBlocked()) {
            throw new readerIsBlockedException("Reader is blocked.");
        }

        if (DAO.getLoanDAO().getAllFromUser(reader.getUsername()).size() > 3){
            throw new fullException("Reader has too many active loans");
        }

        if (book.getAvailableCopies() < 1){
            throw new notFoundException("There are no copies available");
        }

        ArrayList<BookReserve> reserves = DAO.getBookReserveDAO().getReservesFromBook(book.getIsbn());

        int reserveIndex = -1;
        int reservesSize = reserves.size();

        if (!reserves.isEmpty()){
            for (int i = 0; i < reservesSize; i++){
                if (reserves.get(i).getUsername().equals(reader.getUsername())){
                    reserveIndex = i;
                    break;
                }
            }
        }

        if (reserveIndex == -1){ // Se o usuário não possui uma reserva
            if (book.getAvailableCopies() <= reservesSize){ // se a quantidade de copias for <= q a de reservas
                throw new tooManyReservesException("There are too many reserves to current book");
            }
        } else {
            // Se a posição do usuario na fila for maior que a quantidade de cópias disponiveis
            if (reserveIndex >= book.getAvailableCopies()){
                throw new tooManyReservesException("There are too many reserves to current book");
            }
        }

        book.borrowCopy();
        LocalDate today = LocalDate.now();
        Loan newLoan = new Loan(book.getIsbn(), reader.getUsername(), today, days, this.getUsername());

        DAO.getLoanDAO().create(newLoan);
        DAO.getBookDAO().update(book);

        return newLoan;
    }

    public void deleteBookLoan(String loanId){
        Loan toDelete = DAO.getLoanDAO().getByPK(loanId);
        Book book = DAO.getBookDAO().getByPK(toDelete.getBookIsbn());

        book.retrieveCopy();

        DAO.getLoanDAO().deleteByPK(loanId);
        DAO.getBookDAO().update(book);
    }

    public ArrayList<Book> searchBookByTitle(String title){
        return DAO.getBookDAO().searchByTitle(title);
    }
}
