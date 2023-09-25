package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.Loan;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.*;

public class LoanFileImpl extends ConnectionFile implements LoanDAO{

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
        return generateId(loanFileUrl);
    }

    @Override
    public ArrayList<Pair<String, Integer>> getPopularBooksAllTime() {
        HashMap<String, Integer> totalLoansHM = new HashMap<>();
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);

        String newKey;
        Book nowBook;

        for (String key : loanHM.keySet()){

            nowBook = bookHM.get(loanHM.get(key).getBookIsbn());
            newKey = nowBook.getIsbn();

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

    @Override
    public Integer getTotalLoans(){
        return getAnySavedHashmap(loanFileUrl).size();
    }

    @Override
    public Integer getTotalOverdueLoans(){
        HashMap<String, Loan> allLoans = getAnySavedHashmap(loanFileUrl);
        Integer total = 0;

        for (String key : allLoans.keySet()){
            if (allLoans.get(key).getFinalDate().isBefore(LocalDate.now())){
                total ++;
            }
        }

        return total;
    }

    @Override
    public ArrayList<Loan> getAllFromUser(String username){
        HashMap<String, Loan> allLoans = getAnySavedHashmap(loanFileUrl);
        ArrayList<Loan> fromUser = new ArrayList<>();

        for (String key : allLoans.keySet()){
            if (allLoans.get(key).getUsername().equals(username)){
                fromUser.add(allLoans.get(key));
            }
        }

        return fromUser;
    }
}
