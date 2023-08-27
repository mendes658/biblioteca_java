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

    @Override
    public ArrayList<Pair<String, Integer>> getPopularBooksAllTime() {
        HashMap<String, Integer> totalLoansHM = new HashMap<>();
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        HashMap<String, BookCopy> bookCopyHM = getAnySavedHashmap(bookCopiesUrl);

        String newKey;
        Book nowBook;

        for (String key : loanHM.keySet()){

            nowBook = bookCopyHM.get(loanHM.get(key).getbookCopyId());
            newKey = nowBook.getIsbn() + " " + nowBook.getTitle();

            if (totalLoansHM.get(newKey) == null){
                totalLoansHM.put(newKey, 1);
            } else {
                totalLoansHM.put(newKey, totalLoansHM.get(newKey) + 1);
            }
        }


        ArrayList<Pair<String, Integer>> popularOrdered = new ArrayList<>();

        for (String key : totalLoansHM.keySet()){
            popularOrdered.add(new Pair<>(key, totalLoansHM.get(key)));
        }

        popularOrdered.sort(Comparator.comparingInt(Pair<String, Integer>::getValue).reversed());

        return popularOrdered;
    }
}