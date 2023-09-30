package com.pbl.biblioteca.dao.User;

import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.wrongPasswordException;
import com.pbl.biblioteca.model.User;

public interface UserDAO {

    String generateId();
    User login(String username, String password, String type) throws
            wrongPasswordException, notFoundException;

}
