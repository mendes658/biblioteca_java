package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.Loan;
import javafx.util.Pair;

import java.util.ArrayList;

public interface LoanDAO extends CRUD<Loan> {
    String generateId();
    ArrayList<Pair<String, Integer>> getPopularBooksAllTime();

    Integer getTotalLoans();

    Integer getTotalOverdueLoans();

    ArrayList<Loan> getAllFromUser(String username);
}
