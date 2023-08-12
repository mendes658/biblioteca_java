package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.Loan;

import java.io.File;
import java.util.HashMap;

public class LoanDAO extends ConnectionDAO{

    public static boolean saveLoan(Loan loanObject){
        HashMap<String, Loan> loanHM = getLoanHashmap();

        loanHM.put(loanObject.getId(),loanObject);

        return saveAnyHashmap(loanHM, loanFileUrl);
    }

    public static Loan getLoanById(String id){
        HashMap<String, Loan> loanHM = getLoanHashmap();
        return loanHM.get(id);
    }
}
