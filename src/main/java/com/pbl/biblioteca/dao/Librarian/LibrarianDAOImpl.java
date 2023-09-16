package com.pbl.biblioteca.dao.Librarian;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Librarian;

import java.util.HashMap;

public class LibrarianDAOImpl extends ConnectionDAO implements LibrarianDAO{

    @Override
    public boolean create(Librarian librarianObject){
        HashMap<String, Librarian> librarianHM = getAnySavedHashmap(librarianFileUrl);

        librarianHM.put(librarianObject.getUsername(),librarianObject);

        return saveAnyObject(librarianHM, librarianFileUrl);
    }

    @Override
    public Librarian getByPK(String username){
        HashMap<String, Librarian> librarianHM = getAnySavedHashmap(librarianFileUrl);

        return librarianHM.get(username);
    }

    @Override
    public boolean update(Librarian librarianObj) {

        HashMap<String, Librarian> librarianHM = getAnySavedHashmap(librarianFileUrl);
        librarianHM.put(librarianObj.getUsername(), librarianObj);

        return saveAnyObject(librarianHM, librarianFileUrl);
    }

    @Override
    public boolean deleteByPK(String username) {

        HashMap<String, Librarian> librarianHM = getAnySavedHashmap(librarianFileUrl);
        librarianHM.remove(username);

        return saveAnyObject(librarianHM, librarianFileUrl);
    }

    @Override
    public HashMap<String, Librarian> getAll(){
        return getAnySavedHashmap(librarianFileUrl);
    }
}
