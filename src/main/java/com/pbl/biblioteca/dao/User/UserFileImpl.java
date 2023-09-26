package com.pbl.biblioteca.dao.User;


import java.util.HashMap;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.*;

public class UserFileImpl extends ConnectionFile implements UserDAO{

    @Override
    public void create(User readerObject){
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);

        userHM.put(readerObject.getUsername(), readerObject);

        saveAnyObject(userHM, userFileUrl);
    }

    @Override
    public User getByPK(String nickname){
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);

        return userHM.get(nickname);
    }

    @Override
    public void update(User readerObj) {
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);
        userHM.put(readerObj.getUsername(), readerObj);

        saveAnyObject(userHM, userFileUrl);
    }

    @Override
    public void deleteByPK(String username) {
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);
        userHM.remove(username);

        saveAnyObject(userHM, userFileUrl);
    }

    @Override
    public String generateId() {
        return generateId(userFileUrl);
    }

    @Override
    public HashMap<String, User> getAll() {
        return getAnySavedHashmap(userFileUrl);
    }
}
