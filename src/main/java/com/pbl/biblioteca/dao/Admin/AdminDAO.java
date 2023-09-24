package com.pbl.biblioteca.dao.Admin;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.Admin;

public interface AdminDAO extends CRUD<Admin> {

    String generateId();
}
