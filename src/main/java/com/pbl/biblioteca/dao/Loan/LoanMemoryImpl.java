package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.Loan;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.*;

public class LoanMemoryImpl extends ConnectionMemory implements LoanDAO{

    @Override
    public void create(Loan loanObject){
        HashMap<String, Loan> loanHM = getAnySavedHashmap("loan");
        loanHM.put(loanObject.getId(),loanObject);

        saveAnyObject(loanHM, "loan");

    }

    @Override
    public Loan getByPK(String id){
        HashMap<String, Loan> loanHM = getAnySavedHashmap("loan");
        return loanHM.get(id);
    }

    @Override
    public void update(Loan loanObj) {
        HashMap<String, Loan> loanHM = getAnySavedHashmap("loan");
        loanHM.put(loanObj.getId(), loanObj);

        saveAnyObject(loanHM, "loan");

    }

    @Override
    public void deleteByPK(String id) {
        HashMap<String, Loan> loanHM = getAnySavedHashmap("loan");
        loanHM.remove(id);

        saveAnyObject(loanHM, "loan");
    }

    @Override
    public HashMap<String, Loan> getAll(){
        return getAnySavedHashmap("loan");
    }

    @Override
    public String generateId() {
        return generateId("loan");
    }

    @Override
    public ArrayList<Pair<String, Integer>> getPopularBooksAllTime() {
        HashMap<String, Integer> totalLoansHM = new HashMap<>();
        HashMap<String, Loan> loanHM = getAnySavedHashmap("loan");
        HashMap<String, Book> bookHM = getAnySavedHashmap("loan");

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
        return getAnySavedHashmap("loan").size();
    }

    @Override
    public Integer getTotalOverdueLoans(){
        HashMap<String, Loan> allLoans = getAnySavedHashmap("loan");
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
        HashMap<String, Loan> allLoans = getAnySavedHashmap("loan");
        ArrayList<Loan> fromUser = new ArrayList<>();

        for (String key : allLoans.keySet()){
            if (allLoans.get(key).getUsername().equals(username)){
                fromUser.add(allLoans.get(key));
            }
        }

        return fromUser;
    }
}
