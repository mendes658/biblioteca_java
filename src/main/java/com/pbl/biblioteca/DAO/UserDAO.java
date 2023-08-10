package com.pbl.biblioteca.DAO;

import java.io.*;
import java.util.HashMap;

import com.pbl.biblioteca.model.*;

public class UserDAO {

    public static User getUserByUsername(String nickname){
        HashMap<String, User> userHM = ConnectionDAO.getUserHashmap();
        return userHM.get(nickname);
    }


}
