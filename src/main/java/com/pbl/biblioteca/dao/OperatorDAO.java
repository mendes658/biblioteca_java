package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.Operator;

import java.util.HashMap;

public class OperatorDAO extends ConnectionDAO{

    public static boolean saveOperator(Operator operatorObject){
        HashMap<String, Operator> operatorHM = getOperatorHashmap();
        if (operatorHM == null){
            operatorHM = new HashMap<String, Operator>();
        }

        operatorHM.put(operatorObject.getId(),operatorObject);

        return saveAnyHashmap(operatorHM, operatorFileUrl);
    }

}
