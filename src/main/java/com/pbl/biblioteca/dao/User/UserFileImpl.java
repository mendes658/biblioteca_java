package com.pbl.biblioteca.dao.User;


import java.util.HashMap;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.*;

public class UserFileImpl extends ConnectionFile implements UserDAO{

    @Override
    public boolean create(Reader readerObject){
        HashMap<String, Reader> userHM = getAnySavedHashmap(userFileUrl);

        userHM.put(readerObject.getUsername(), readerObject);

        return saveAnyObject(userHM, userFileUrl);
    }

    @Override
    public Reader getByPK(String nickname){
        HashMap<String, Reader> userHM = getAnySavedHashmap(userFileUrl);

        return userHM.get(nickname);
    }

    @Override
    public boolean update(Reader readerObj) {
        HashMap<String, Reader> userHM = getAnySavedHashmap(userFileUrl);
        userHM.put(readerObj.getUsername(), readerObj);

        return saveAnyObject(userHM, userFileUrl);
    }

    @Override
    public boolean deleteByPK(String username) {
        HashMap<String, Reader> userHM = getAnySavedHashmap(userFileUrl);
        userHM.remove(username);

        return saveAnyObject(userHM, userFileUrl);
    }

    @Override
    public String generateId() {
        return ConnectionFile.generateId(userFileUrl);
    }

    @Override
    public HashMap<String, Reader> getAll() {
        return getAnySavedHashmap(userFileUrl);
    }
}
