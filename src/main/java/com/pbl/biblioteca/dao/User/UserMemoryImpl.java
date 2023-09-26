package com.pbl.biblioteca.dao.User;


import java.util.HashMap;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.*;

public class UserMemoryImpl extends ConnectionMemory implements UserDAO{

    @Override
    public void create(User readerObject){
        HashMap<String, User> userHM = getAnySavedHashmap("user");

        userHM.put(readerObject.getUsername(), readerObject);

        saveAnyObject(userHM, "user");
    }

    @Override
    public User getByPK(String nickname){
        HashMap<String, User> userHM = getAnySavedHashmap("user");

        return userHM.get(nickname);
    }

    @Override
    public void update(User readerObj) {
        HashMap<String, User> userHM = getAnySavedHashmap("user");
        userHM.put(readerObj.getUsername(), readerObj);

        saveAnyObject(userHM, "user");
    }

    @Override
    public void deleteByPK(String username) {
        HashMap<String, User> userHM = getAnySavedHashmap("user");
        userHM.remove(username);

        saveAnyObject(userHM, "user");
    }

    @Override
    public String generateId() {
        return generateId("user");
    }

    @Override
    public HashMap<String, User> getAll() {
        return getAnySavedHashmap("user");
    }
}
