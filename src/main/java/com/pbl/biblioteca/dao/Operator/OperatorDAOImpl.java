package com.pbl.biblioteca.dao.Operator;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Operator;

import java.util.HashMap;

public class OperatorDAOImpl extends ConnectionDAO implements OperatorDAO<Operator>{

    @Override
    public boolean create(Operator operatorObject){
        HashMap<String, Operator> operatorHM = getAnySavedHashmap(operatorFileUrl);

        operatorHM.put(operatorObject.getUsername(),operatorObject);

        return saveAnyObject(operatorHM, operatorFileUrl);
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
    public boolean update(Operator operatorObj) {
        HashMap<String, Operator> operatorHM = getAnySavedHashmap(operatorFileUrl);
        operatorHM.put(operatorObj.getUsername(), operatorObj);

        saveAnyObject(operatorHM, operatorFileUrl);
        return true;
    }

    @Override
    public String generateId() {
        return ConnectionDAO.generateId(operatorFileUrl);
    }

    @Override
    public boolean deleteByPK(String username) {
        HashMap<String, Operator> operatorHM = getAnySavedHashmap(operatorFileUrl);
        operatorHM.remove(username);
        return true;
    }
}
