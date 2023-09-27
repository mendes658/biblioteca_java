package com.pbl.biblioteca.dao.User;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.User;

public interface UserDAO extends CRUD<User> {

    String generateId();


}
