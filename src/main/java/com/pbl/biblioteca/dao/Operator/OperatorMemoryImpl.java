package com.pbl.biblioteca.dao.Operator;


import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.Operator;

import java.util.HashMap;

public class OperatorMemoryImpl extends ConnectionMemory implements OperatorDAO{

    @Override
    public void create(Operator operatorObject){
        HashMap<String, Operator> operatorHM = getAnySavedHashmap("operator");

        operatorHM.put(operatorObject.getUsername(),operatorObject);

        saveAnyObject(operatorHM, "operator");
    }

    @Override
    public Operator getByPK(String username) {
        HashMap<String, Operator> operatorHM = getAnySavedHashmap("operator");
        return operatorHM.get(username);
    }

    @Override
    public HashMap<String, Operator> getAll() {
        return getAnySavedHashmap("operator");
    }

    @Override
    public void update(Operator operatorObj) {
        HashMap<String, Operator> operatorHM = getAnySavedHashmap("operator");
        operatorHM.put(operatorObj.getUsername(), operatorObj);

        saveAnyObject(operatorHM, "operator");
    }

    @Override
    public void deleteByPK(String username) {
        HashMap<String, Operator> operatorHM = getAnySavedHashmap("operator");
        operatorHM.remove(username);
    }
}
