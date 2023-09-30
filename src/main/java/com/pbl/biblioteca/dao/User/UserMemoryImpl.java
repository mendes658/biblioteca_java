package com.pbl.biblioteca.dao.User;


import java.util.HashMap;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.wrongPasswordException;
import com.pbl.biblioteca.model.*;

public class UserMemoryImpl extends ConnectionMemory implements UserDAO{

    @Override
    public void create(User readerObject){
        HashMap<String, User> userHM = getAnySavedHashmap("user");

        userHM.put(readerObject.getUsername(), readerObject);

        saveAnyObject(userHM, "user");
    }

    @Override
    public User getByPK(String nickname){
        HashMap<String, User> userHM = getAnySavedHashmap("user");

        return userHM.get(nickname);
    }

    @Override
    public void update(User readerObj) {
        HashMap<String, User> userHM = getAnySavedHashmap("user");
        userHM.put(readerObj.getUsername(), readerObj);

        saveAnyObject(userHM, "user");
    }

    @Override
    public void deleteByPK(String username) {
        HashMap<String, User> userHM = getAnySavedHashmap("user");
        userHM.remove(username);

        saveAnyObject(userHM, "user");
    }

    @Override
    public String generateId() {
        return generateId("user");
    }

    @Override
    public HashMap<String, User> getAll() {
        return getAnySavedHashmap("user");
    }

    @Override
    public User login(String username, String password, String type) throws
            wrongPasswordException, notFoundException {
        switch (type){
            case "reader" -> {
                Reader r1 = DAO.getReaderDAO().getByPK(username);
                if (r1 != null){
                    if (r1.getPassword().equals(password)){
                        return r1;
                    } else {
                        throw new wrongPasswordException("Wrong password");
                    }
                } else {
                    throw new notFoundException("Reader does not exist");
                }
            }
            case "librarian" -> {
                Librarian l1 = DAO.getLibrarianDAO().getByPK(username);
                if (l1 != null){
                    if (l1.getPassword().equals(password)){
                        return l1;
                    } else {
                        throw new wrongPasswordException("Wrong password");
                    }
                } else {
                    throw new notFoundException("Librarian does not exist");
                }
            }
            case "admin" -> {
                Admin a1 = DAO.getAdminDAO().getByPK(username);
                if (a1 != null){
                    if (a1.getPassword().equals(password)){
                        return a1;
                    } else {
                        throw new wrongPasswordException("Wrong password");
                    }
                } else {
                    throw new notFoundException("Admin does not exist");
                }
            }
        }

        throw new notFoundException("User does not exist");
    }
}
