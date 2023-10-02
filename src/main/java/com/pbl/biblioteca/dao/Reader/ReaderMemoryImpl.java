package com.pbl.biblioteca.dao.Reader;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.Reader;

import java.util.HashMap;

public class ReaderMemoryImpl extends ConnectionMemory implements ReaderDAO{

    /**
     * Salva um objeto Reader na memória
     * @param  readerObject Reader que será salvo
     */
    @Override
    public void create(Reader readerObject){
        HashMap<String, Reader> userHM = getAnySavedHashmap("reader");

        userHM.put(readerObject.getUsername(), readerObject);

        saveAnyObject(userHM, "reader");
    }

    /**
     * Pega um objeto Reader salvo, através da primary key
     * @param  username O username é a primary key
     * @return Retorna o objeto Reader
     */
    @Override
    public Reader getByPK(String username){
        HashMap<String, Reader> userHM = getAnySavedHashmap("reader");

        return userHM.get(username);
    }

    /**
     * Atualiza um objeto Reader na memória
     * @param  readerObj Reader que será atualizado
     */
    @Override
    public void update(Reader readerObj) {
        HashMap<String, Reader> userHM = getAnySavedHashmap("reader");
        userHM.put(readerObj.getUsername(), readerObj);

        saveAnyObject(userHM, "reader");
    }

    /**
     * Deleta um objeto Reader da memória
     * @param username Primary key do Reader
     */
    @Override
    public void deleteByPK(String username) {
        HashMap<String, Reader> userHM = getAnySavedHashmap("reader");
        userHM.remove(username);

        saveAnyObject(userHM, "reader");
    }

    /**
     * Pega todos os Readers salvos atualmente
     * @return Retorna um Hashmap com todos os Readers, a key é o username
     */
    @Override
    public HashMap<String, Reader> getAll() {
        return getAnySavedHashmap("reader");
    }
}
