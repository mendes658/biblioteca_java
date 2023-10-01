package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.isbnAlreadyInUseException;
import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.usernameAlreadyInUseException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Admin extends Operator implements Serializable {

    public Admin(String username, String password, String address, String telephone,
                     String name){
        super(username, password, address, telephone, name, "admin");

    }

    public User createUser(String username, String password, String address, String telephone,
                           String name, String type) throws usernameAlreadyInUseException {

        switch (type) {
            case "reader" -> {
                Reader reader = DAO.getReaderDAO().getByPK(username);

                if (reader != null){
                    throw new usernameAlreadyInUseException("Username in use");
                }

                reader = new Reader(username, name, address, telephone, password);
                DAO.getReaderDAO().create(reader);
                return reader;
            }
            case "librarian" -> {
                Librarian librarian = DAO.getLibrarianDAO().getByPK(username);

                if (librarian != null){
                    throw new usernameAlreadyInUseException("Username in use");
                }

                librarian = new Librarian(username, password, address, telephone, name);
                DAO.getLibrarianDAO().create(librarian);
                return librarian;
            }
            case "admin" -> {
                Admin admin = DAO.getAdminDAO().getByPK(username);

                if (admin != null){
                    throw new usernameAlreadyInUseException("Username in use");
                }

                admin = new Admin(username, password, address, telephone, name);
                DAO.getAdminDAO().create(admin);
                return admin;
            }
        }

        return null;
    }


    public void deleteUser(User user){
       String type = user.getType();

       switch (type){
           case "reader" -> {
               DAO.getReaderDAO().deleteByPK(user.getUsername());
           }
           case "admin" -> {
               DAO.getAdminDAO().deleteByPK(user.getUsername());
           }
           case "librarian" -> {
               DAO.getLibrarianDAO().deleteByPK(user.getUsername());
           }
       }
    }

    public void updateUser(User user){
        String type = user.getType();

        switch (type){
            case "reader" -> {
                DAO.getReaderDAO().update((Reader) user);
            }
            case "admin" -> {
                DAO.getAdminDAO().update((Admin) user);
            }
            case "librarian" -> {
                DAO.getLibrarianDAO().update((Librarian) user);
            }
        }
    }

    public User getUser(String username, String type) throws notFoundException {
        switch (type){
            case "reader" -> {
                return DAO.getReaderDAO().getByPK(username);
            }
            case "admin" -> {
                return DAO.getAdminDAO().getByPK(username);
            }
            case "librarian" -> {
                return DAO.getLibrarianDAO().getByPK(username);
            }
        }

        throw new notFoundException("User not found");
    }

    public void blockReader(Reader reader, Integer days){
        if (days == -1){
            reader.setDateEndBlock(null);
        } else {
            reader.setDateEndBlock(LocalDate.now().plusDays(days));
        }

        reader.setBlocked(true);
        DAO.getReaderDAO().update(reader);
    }

    public void unblockReader(Reader reader){
        reader.setBlocked(false);
        reader.setDateEndBlock(null);
        DAO.getReaderDAO().update(reader);
    }

    public void addBookCopies(Book book, Integer total){
        book.addCopies(total);

        DAO.getBookDAO().update(book);
    }

    public void removeBookCopies(Book book, Integer total) throws notFoundException{
        book.removeCopies(total);

        DAO.getBookDAO().update(book);
    }

    public Book createBook(String title, String author, String publisher,
                           Integer year, String category, String isbn, Integer totalCopies)
    throws isbnAlreadyInUseException {

        if (DAO.getBookDAO().getByPK(isbn) != null){
            throw new isbnAlreadyInUseException("The given isbn is already in use");
        }

        Book b1 = new Book(title, author, publisher, year, category, isbn, totalCopies);
        DAO.getBookDAO().create(b1);

        return b1;
    }

    public void deleteBook(Book book){
        ArrayList<BookReserve> reservesBook = DAO.getBookReserveDAO().getAllFromBook(book.getIsbn());
        ArrayList<Loan> loansBook = DAO.getLoanDAO().getAllFromBook(book.getIsbn());

        for(BookReserve reserve : reservesBook){
            DAO.getBookDAO().deleteByPK(reserve.getId());
        }

        for(Loan lo : loansBook){
            DAO.getLoanDAO().deleteByPK(lo.getId());
        }

        DAO.getBookDAO().deleteByPK(book.getIsbn());
    }

    public void updateBook(Book book){
        DAO.getBookDAO().update(book);
    }
}
