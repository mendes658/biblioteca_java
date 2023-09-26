package com.pbl.biblioteca.dao.Librarian;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.Librarian;

import java.util.HashMap;

public class LibrarianMemoryImpl extends ConnectionMemory implements LibrarianDAO{

    @Override
    public void create(Librarian librarianObject){
        HashMap<String, Librarian> librarianHM = getAnySavedHashmap("librarian");

        librarianHM.put(librarianObject.getUsername(),librarianObject);

        saveAnyObject(librarianHM, "librarian");
    }

    @Override
    public Librarian getByPK(String username){
        HashMap<String, Librarian> librarianHM = getAnySavedHashmap("librarian");

        return librarianHM.get(username);
    }

    @Override
    public void update(Librarian librarianObj) {

        HashMap<String, Librarian> librarianHM = getAnySavedHashmap("librarian");
        librarianHM.put(librarianObj.getUsername(), librarianObj);

        saveAnyObject(librarianHM, "librarian");
    }

    @Override
    public void deleteByPK(String username) {

        HashMap<String, Librarian> librarianHM = getAnySavedHashmap("librarian");
        librarianHM.remove(username);

        saveAnyObject(librarianHM, "librarian");
    }

    @Override
    public HashMap<String, Librarian> getAll(){
        return getAnySavedHashmap("librarian");
    }
}
