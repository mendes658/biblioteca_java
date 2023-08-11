package com.pbl.biblioteca.dao;


import java.util.HashMap;

import com.pbl.biblioteca.model.*;

public class UserDAO extends ConnectionDAO{

    public static boolean saveUser(User userObject){
        HashMap<String, User> userHM = getUserHashmap();
        if (userHM == null){
            userHM = new HashMap<String, User>();
        }

        userHM.put(userObject.getNickname(),userObject);

        return saveAnyHashmap(userHM, userFileUrl);
    }

    public static User getUserByUsername(String nickname){
        HashMap<String, User> userHM = ConnectionDAO.getUserHashmap();
        return userHM.get(nickname);
    }


}
