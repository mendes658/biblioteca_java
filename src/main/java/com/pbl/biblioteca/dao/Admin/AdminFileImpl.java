package com.pbl.biblioteca.dao.Admin;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Admin;

import java.util.HashMap;

public class AdminFileImpl extends ConnectionFile implements AdminDAO{

    @Override
    public boolean create(Admin adminObject){
        HashMap<String, Admin> operatorHM = getAnySavedHashmap(adminUrl);

        operatorHM.put(adminObject.getUsername(),adminObject);

        return saveAnyObject(operatorHM, adminUrl);
    }

    @Override
    public Admin getByPK(String username) {
        HashMap<String, Admin> operatorHM = getAnySavedHashmap(adminUrl);
        return operatorHM.get(username);
    }

    @Override
    public HashMap<String, Admin> getAll() {
        return getAnySavedHashmap(adminUrl);
    }

    @Override
    public boolean update(Admin operatorObj) {
        HashMap<String, Admin> operatorHM = getAnySavedHashmap(adminUrl);
        operatorHM.put(operatorObj.getUsername(), operatorObj);

        saveAnyObject(operatorHM, adminUrl);
        return true;
    }

    @Override
    public String generateId() {
        return ConnectionFile.generateId(adminUrl);
    }

    @Override
    public boolean deleteByPK(String username) {
        HashMap<String, Admin> operatorHM = getAnySavedHashmap(adminUrl);
        operatorHM.remove(username);
        return true;
    }
}
