package com.pbl.biblioteca.dao.User;


import java.util.HashMap;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.*;

public class UserFileImpl extends ConnectionFile implements UserDAO{

    @Override
    public boolean create(User readerObject){
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);

        userHM.put(readerObject.getUsername(), readerObject);

        return saveAnyObject(userHM, userFileUrl);
    }

    @Override
    public User getByPK(String nickname){
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);

        return userHM.get(nickname);
    }

    @Override
    public boolean update(User readerObj) {
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);
        userHM.put(readerObj.getUsername(), readerObj);

        return saveAnyObject(userHM, userFileUrl);
    }

    @Override
    public boolean deleteByPK(String username) {
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);
        userHM.remove(username);

        return saveAnyObject(userHM, userFileUrl);
    }

    @Override
    public String generateId() {
        return ConnectionFile.generateId(userFileUrl);
    }

    @Override
    public HashMap<String, User> getAll() {
        return getAnySavedHashmap(userFileUrl);
    }
}
