package com.pbl.biblioteca.dao.Admin;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Admin;

import java.util.HashMap;

public class AdminFileImpl extends ConnectionFile implements AdminDAO{

    @Override
    public void create(Admin adminObject){
        HashMap<String, Admin> adminHm = getAnySavedHashmap(adminUrl);

        adminHm.put(adminObject.getUsername(),adminObject);

        saveAnyObject(adminHm, adminUrl);
    }

    @Override
    public Admin getByPK(String username) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap(adminUrl);
        return adminHm.get(username);
    }

    @Override
    public HashMap<String, Admin> getAll() {
        return getAnySavedHashmap(adminUrl);
    }

    @Override
    public void update(Admin operatorObj) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap(adminUrl);
        adminHm.put(operatorObj.getUsername(), operatorObj);

        saveAnyObject(adminHm, adminUrl);
    }

    @Override
    public void deleteByPK(String username) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap(adminUrl);
        adminHm.remove(username);
    }
}
