package com.pbl.biblioteca.dao.User;

import com.pbl.biblioteca.dao.crudDAO;

public interface UserDAO<User> extends crudDAO<User> {

    String generateId();
}
