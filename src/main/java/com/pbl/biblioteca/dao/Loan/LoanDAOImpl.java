package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.Book.BookDAOImpl;
import com.pbl.biblioteca.dao.BookCopy.BookCopyDAOImpl;
import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.BookCopy;
import com.pbl.biblioteca.model.Loan;
import javafx.util.Pair;

import java.util.*;

public class LoanDAOImpl extends ConnectionDAO implements LoanDAO<Loan>{

    @Override
    public boolean create(Loan loanObject){
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        loanHM.put(loanObject.getId(),loanObject);

        saveAnyObject(loanHM, loanFileUrl);
        updateTotalLoansFile(loanObject);

        return true;
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

    private void updateTotalLoansFile(Loan loanObject){
        HashMap<String, Integer> totalLoansHM = getAnySavedHashmap(totalLoansByBookUrl);
        BookCopyDAOImpl copyDAO = new BookCopyDAOImpl();
        BookCopy bookObj = null;
        String bookIsbnName = null;
        try {
            bookObj = copyDAO.getByPK(loanObject.getbookCopyId());
            bookIsbnName = bookObj.getIsbn() + " " + bookObj.getTitle();
        } catch (Exception e){
            e.printStackTrace();
            return;
        }


        Integer total = totalLoansHM.computeIfAbsent(bookIsbnName, k -> 0);
        totalLoansHM.put(bookIsbnName, total+1);

        saveAnyObject(totalLoansHM, totalLoansByBookUrl);
    }

    @Override
    public ArrayList<Pair<String, Integer>> getPopularBooksAllTime() {
        HashMap<String, Integer> totalLoansHM = getAnySavedHashmap(totalLoansByBookUrl);
        ArrayList<Pair<String, Integer>> popularOrdered = new ArrayList<>();

        for (String key : totalLoansHM.keySet()){
            popularOrdered.add(new Pair<>(key, totalLoansHM.get(key)));
        }

        popularOrdered.sort(Comparator.comparingInt(Pair<String, Integer>::getValue).reversed());

        return popularOrdered;
    }
}
