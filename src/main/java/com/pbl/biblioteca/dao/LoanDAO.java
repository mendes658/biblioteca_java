package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.Loan;

import java.util.HashMap;

public class LoanDAO extends ConnectionDAO{

    public static boolean saveLoan(Loan loanObject){
        HashMap<String, Loan> loanHM = getLoanHashmap();
        if (loanHM == null){
            loanHM = new HashMap<String, Loan>();
        }

        loanHM.put(loanObject.getId(),loanObject);

        return saveAnyHashmap(loanHM, loanFileUrl);
    }


}
