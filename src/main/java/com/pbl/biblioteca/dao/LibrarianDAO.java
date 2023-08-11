package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.Librarian;

import java.util.HashMap;

public class LibrarianDAO extends ConnectionDAO{

    public static boolean saveLibrarian(Librarian librarianObject){
        HashMap<String, Librarian> librarianHM = getLibrarianHashmap();
        if (librarianHM == null){
            librarianHM = new HashMap<>();
        }

        librarianHM.put(librarianObject.getUsername(),librarianObject);

        return saveAnyHashmap(librarianHM, bookFileUrl);
    }

}
