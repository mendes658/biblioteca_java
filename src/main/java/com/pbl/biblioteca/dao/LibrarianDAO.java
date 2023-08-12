package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.Librarian;

import java.util.HashMap;

public class LibrarianDAO extends ConnectionDAO{

    public static boolean saveLibrarian(Librarian librarianObject){
        HashMap<String, Librarian> librarianHM = getLibrarianHashmap();

        librarianHM.put(librarianObject.getUsername(),librarianObject);

        return saveAnyHashmap(librarianHM, librarianFileUrl);
    }

    public static Librarian getLibrarianByUsername(String username){
        return getLibrarianHashmap().get(username);
    }

}
