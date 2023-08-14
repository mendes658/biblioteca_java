package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Loan;

import java.util.HashMap;

public class LoanDAOImpl extends ConnectionDAO implements LoanDAO<Loan>{

    @Override
    public boolean create(Loan loanObject){
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);

        loanHM.put(loanObject.getId(),loanObject);

        return saveAnyObject(loanHM, loanFileUrl);
    }

    @Override
    public Loan getByPK(String id){
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        return loanHM.get(id);
    }

    @Override
    public boolean update(Loan loanObj) {
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        loanHM.put(loanObj.getId(), loanObj);

        saveAnyObject(loanHM, loanFileUrl);

        return true;
    }

    @Override
    public boolean deleteByPK(String id) {
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        loanHM.remove(id);

        return saveAnyObject(loanHM, loanFileUrl);
    }

    @Override
    public HashMap<String, Loan> getAll(){
        return getAnySavedHashmap(loanFileUrl);
    }

    @Override
    public String generateId() {
        return ConnectionDAO.generateId(loanFileUrl);
    }
}
