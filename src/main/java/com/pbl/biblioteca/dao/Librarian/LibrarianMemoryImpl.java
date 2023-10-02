package com.pbl.biblioteca.dao.Librarian;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.Librarian;

import java.util.HashMap;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class LibrarianMemoryImpl extends ConnectionMemory implements LibrarianDAO{

    /**
     * Salva um objeto Librarian na memória
     * @param  librarianObject Librarian que será salvo
     */
    @Override
    public void create(Librarian librarianObject){
        HashMap<String, Librarian> librarianHM = getAnySavedHashmap("librarian");

        librarianHM.put(librarianObject.getUsername(),librarianObject);

        saveAnyObject(librarianHM, "librarian");
    }

    /**
     * Pega um objeto Librarian salvo, através da primary key
     * @param  username O username é a primary key dos Users
     * @return Retorna o objeto Librarian
     */
    @Override
    public Librarian getByPK(String username){
        HashMap<String, Librarian> librarianHM = getAnySavedHashmap("librarian");

        return librarianHM.get(username);
    }

    /**
     * Atualiza um objeto Librarian na memória
     * @param  librarianObj Librarian que será atualizado
     */
    @Override
    public void update(Librarian librarianObj) {

        HashMap<String, Librarian> librarianHM = getAnySavedHashmap("librarian");
        librarianHM.put(librarianObj.getUsername(), librarianObj);

        saveAnyObject(librarianHM, "librarian");
    }

    /**
     * Deleta um objeto Librarian de um arquivo
     * @param  username Primary key do Librarian
     */
    @Override
    public void deleteByPK(String username) {

        HashMap<String, Librarian> librarianHM = getAnySavedHashmap("librarian");
        librarianHM.remove(username);

        saveAnyObject(librarianHM, "librarian");
    }

    /**
     * Pega todos os Librarians salvos atualmente
     * @return Retorna um Hashmap com todos as Librarians, a key é o username
     */
    @Override
    public HashMap<String, Librarian> getAll(){
        return getAnySavedHashmap("librarian");
    }
}
