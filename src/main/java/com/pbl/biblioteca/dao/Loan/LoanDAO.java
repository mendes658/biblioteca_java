package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.crudDAO;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoanDAO<Loan> extends crudDAO<Loan>{
    String generateId();
    ArrayList<Pair<String, Integer>> getPopularBooksAllTime();
}
