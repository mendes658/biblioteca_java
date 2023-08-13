package com.pbl.biblioteca.dao.User;


import java.util.HashMap;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.*;

public class UserDAOImpl extends ConnectionDAO {

    public static boolean saveUser(User userObject){
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);

        userHM.put(userObject.getNickname(),userObject);

        return saveAnyHashmap(userHM, userFileUrl);
    }

    public static User getUserByUsername(String nickname){
        HashMap<String, User> userHM = getAnySavedHashmap(userFileUrl);

        return userHM.get(nickname);
    }


}
