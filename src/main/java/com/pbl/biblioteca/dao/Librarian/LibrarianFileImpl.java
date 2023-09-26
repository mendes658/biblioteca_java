package com.pbl.biblioteca.dao.Librarian;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Librarian;

import java.util.HashMap;

public class LibrarianFileImpl extends ConnectionFile implements LibrarianDAO{

    @Override
    public void create(Librarian librarianObject){
        HashMap<String, Librarian> librarianHM = getAnySavedHashmap(librarianFileUrl);

        librarianHM.put(librarianObject.getUsername(),librarianObject);

        saveAnyObject(librarianHM, librarianFileUrl);
    }

    @Override
    public Librarian getByPK(String username){
        HashMap<String, Librarian> librarianHM = getAnySavedHashmap(librarianFileUrl);

        return librarianHM.get(username);
    }

    @Override
    public void update(Librarian librarianObj) {

        HashMap<String, Librarian> librarianHM = getAnySavedHashmap(librarianFileUrl);
        librarianHM.put(librarianObj.getUsername(), librarianObj);

        saveAnyObject(librarianHM, librarianFileUrl);
    }

    @Override
    public void deleteByPK(String username) {

        HashMap<String, Librarian> librarianHM = getAnySavedHashmap(librarianFileUrl);
        librarianHM.remove(username);

        saveAnyObject(librarianHM, librarianFileUrl);
    }

    @Override
    public HashMap<String, Librarian> getAll(){
        return getAnySavedHashmap(librarianFileUrl);
    }
}
