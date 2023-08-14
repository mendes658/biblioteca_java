package com.pbl.biblioteca.dao.User;


import java.util.HashMap;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.*;

public class UserDAOImpl extends ConnectionDAO implements UserDAO<User>{

    @Override
    public boolean create(User userObject){
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);

        userHM.put(userObject.getUsername(),userObject);

        return saveAnyObject(userHM, userFileUrl);
    }

    @Override
    public User getByPK(String nickname){
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);

        return userHM.get(nickname);
    }

    @Override
    public boolean update(User userObj) {
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);
        userHM.put(userObj.getUsername(), userObj);

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
        return ConnectionDAO.generateId(userFileUrl);
    }

    @Override
    public HashMap<String, User> getAll() {
        return getAnySavedHashmap(userFileUrl);
    }
}
