package com.pbl.biblioteca.dao.Operator;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Operator;

import java.util.HashMap;

public class OperatorDAOImpl extends ConnectionDAO {

    public static boolean saveOperator(Operator operatorObject){
        HashMap<String, Operator> operatorHM = getAnySavedHashmap(operatorFileUrl);

        operatorHM.put(operatorObject.getId(),operatorObject);

        return saveAnyHashmap(operatorHM, operatorFileUrl);
    }

}
