package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.Loan;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public interface LoanDAO extends CRUD<Loan> {
    String generateId();
    ArrayList<Pair<String, Integer>> getPopularBooksToday();

    Integer getTotalLoans();

    Integer getTotalOverdueLoans(LocalDate now);

    ArrayList<Loan> getAllFromUser(String username);
}
