package com.pbl.biblioteca.dao.Librarian;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Librarian;

import java.util.HashMap;

public class LibrarianDAOImpl extends ConnectionDAO {

    public static boolean saveLibrarian(Librarian librarianObject){
        HashMap<String, Librarian> librarianHM = getAnySavedHashmap(librarianFileUrl);

        librarianHM.put(librarianObject.getUsername(),librarianObject);

        return saveAnyHashmap(librarianHM, librarianFileUrl);
    }

    public static Librarian getLibrarianByUsername(String username){
        HashMap<String, Librarian> librarianHM = getAnySavedHashmap(librarianFileUrl);

        return librarianHM.get(username);
    }

}
