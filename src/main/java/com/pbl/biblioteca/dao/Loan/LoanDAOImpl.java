package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Loan;

import java.util.HashMap;

public class LoanDAOImpl extends ConnectionDAO {

    public static boolean saveLoan(Loan loanObject){
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);

        loanHM.put(loanObject.getId(),loanObject);

        return saveAnyHashmap(loanHM, loanFileUrl);
    }

    public static Loan getLoanById(String id){
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        return loanHM.get(id);
    }
}
