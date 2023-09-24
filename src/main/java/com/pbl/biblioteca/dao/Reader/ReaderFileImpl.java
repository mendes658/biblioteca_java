package com.pbl.biblioteca.dao.Reader;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Reader;

import java.util.HashMap;

public class ReaderFileImpl extends ConnectionFile implements ReaderDAO{

    @Override
    public boolean create(Reader readerObject){
        HashMap<String, Reader> userHM = getAnySavedHashmap(readerUrl);

        userHM.put(readerObject.getUsername(), readerObject);

        return saveAnyObject(userHM, readerUrl);
    }

    @Override
    public Reader getByPK(String username){
        HashMap<String, Reader> userHM = getAnySavedHashmap(readerUrl);

        return userHM.get(username);
    }

    @Override
    public boolean update(Reader readerObj) {
        HashMap<String, Reader> userHM = getAnySavedHashmap(readerUrl);
        userHM.put(readerObj.getUsername(), readerObj);

        return saveAnyObject(userHM, readerUrl);
    }

    @Override
    public boolean deleteByPK(String username) {
        HashMap<String, Reader> userHM = getAnySavedHashmap(readerUrl);
        userHM.remove(username);

        return saveAnyObject(userHM, readerUrl);
    }

    @Override
    public String generateId() {
        return ConnectionFile.generateId(readerUrl);
    }

    @Override
    public HashMap<String, Reader> getAll() {
        return getAnySavedHashmap(readerUrl);
    }
}
