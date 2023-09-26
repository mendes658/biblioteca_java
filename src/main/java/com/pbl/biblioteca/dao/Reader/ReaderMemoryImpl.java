package com.pbl.biblioteca.dao.Reader;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.Reader;

import java.util.HashMap;

public class ReaderMemoryImpl extends ConnectionMemory implements ReaderDAO{

    @Override
    public void create(Reader readerObject){
        HashMap<String, Reader> userHM = getAnySavedHashmap("reader");

        userHM.put(readerObject.getUsername(), readerObject);

        saveAnyObject(userHM, "reader");
    }

    @Override
    public Reader getByPK(String username){
        HashMap<String, Reader> userHM = getAnySavedHashmap("reader");

        return userHM.get(username);
    }

    @Override
    public void update(Reader readerObj) {
        HashMap<String, Reader> userHM = getAnySavedHashmap("reader");
        userHM.put(readerObj.getUsername(), readerObj);

        saveAnyObject(userHM, "reader");
    }

    @Override
    public void deleteByPK(String username) {
        HashMap<String, Reader> userHM = getAnySavedHashmap("reader");
        userHM.remove(username);

        saveAnyObject(userHM, "reader");
    }

    @Override
    public HashMap<String, Reader> getAll() {
        return getAnySavedHashmap("reader");
    }
}
