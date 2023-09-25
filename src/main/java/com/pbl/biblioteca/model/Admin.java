package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.notFoundException;

import java.io.Serializable;
import java.time.LocalDate;

public class Admin extends Operator implements Serializable {

    public Admin(String username, String password, String address, String telephone,
                     String name){
        super(username, password, address, telephone, name, "admin");

    }

    public User createUser(String username, String password, String address, String telephone,
                           String name, String type){

        switch (type) {
            case "reader" -> {
                Reader reader = new Reader(username, name, address, telephone, password);
                DAO.getReaderDAO().create(reader);
                return reader;
            }
            case "librarian" -> {
                Librarian librarian = new Librarian(username, password, address, telephone, name);
                DAO.getLibrarianDAO().create(librarian);
                return librarian;
            }
            case "admin" -> {
                Admin admin = new Admin(username, password, address, telephone, name);
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
        DAO.getReaderDAO().update(reader);
    }
}
