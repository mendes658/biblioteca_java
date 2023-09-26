package com.pbl.biblioteca.dao.Reader;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Reader;

import java.util.HashMap;

public class ReaderFileImpl extends ConnectionFile implements ReaderDAO{

    @Override
    public void create(Reader readerObject){
        HashMap<String, Reader> userHM = getAnySavedHashmap(readerUrl);

        userHM.put(readerObject.getUsername(), readerObject);

        saveAnyObject(userHM, readerUrl);
    }

    @Override
    public Reader getByPK(String username){
        HashMap<String, Reader> userHM = getAnySavedHashmap(readerUrl);

        return userHM.get(username);
    }

    @Override
    public void update(Reader readerObj) {
        HashMap<String, Reader> userHM = getAnySavedHashmap(readerUrl);
        userHM.put(readerObj.getUsername(), readerObj);

        saveAnyObject(userHM, readerUrl);
    }

    @Override
    public void deleteByPK(String username) {
        HashMap<String, Reader> userHM = getAnySavedHashmap(readerUrl);
        userHM.remove(username);

        saveAnyObject(userHM, readerUrl);
    }

    @Override
    public HashMap<String, Reader> getAll() {
        return getAnySavedHashmap(readerUrl);
    }
}
