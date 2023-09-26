package com.pbl.biblioteca.dao.Operator;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Operator;

import java.util.HashMap;

public class OperatorFileImpl extends ConnectionFile implements OperatorDAO{

    @Override
    public void create(Operator operatorObject){
        HashMap<String, Operator> operatorHM = getAnySavedHashmap(operatorFileUrl);

        operatorHM.put(operatorObject.getUsername(),operatorObject);

        saveAnyObject(operatorHM, operatorFileUrl);
    }

    @Override
    public Operator getByPK(String username) {
        HashMap<String, Operator> operatorHM = getAnySavedHashmap(operatorFileUrl);
        return operatorHM.get(username);
    }

    @Override
    public HashMap<String, Operator> getAll() {
        return getAnySavedHashmap(operatorFileUrl);
    }

    @Override
    public void update(Operator operatorObj) {
        HashMap<String, Operator> operatorHM = getAnySavedHashmap(operatorFileUrl);
        operatorHM.put(operatorObj.getUsername(), operatorObj);

        saveAnyObject(operatorHM, operatorFileUrl);
    }

    @Override
    public void deleteByPK(String username) {
        HashMap<String, Operator> operatorHM = getAnySavedHashmap(operatorFileUrl);
        operatorHM.remove(username);
    }
}
