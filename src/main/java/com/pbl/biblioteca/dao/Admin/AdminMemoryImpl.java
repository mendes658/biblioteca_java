package com.pbl.biblioteca.dao.Admin;


import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.Admin;

import java.util.HashMap;

public class AdminMemoryImpl extends ConnectionMemory implements AdminDAO{

    @Override
    public void create(Admin adminObject){
        HashMap<String, Admin> adminHm = getAnySavedHashmap("admin");

        adminHm.put(adminObject.getUsername(), adminObject);
        saveAnyObject(adminHm, "admin");
    }

    @Override
    public Admin getByPK(String username) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap("admin");

        return adminHm.get(username);
    }

    @Override
    public HashMap<String, Admin> getAll() {
        return getAnySavedHashmap("admin");
    }

    @Override
    public void update(Admin operatorObj) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap("admin");
        adminHm.put(operatorObj.getUsername(), operatorObj);

        saveAnyObject(adminHm, "admin");
    }

    @Override
    public void deleteByPK(String username) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap("admin");
        adminHm.remove(username);

        saveAnyObject(adminHm, "admin");
    }
}
