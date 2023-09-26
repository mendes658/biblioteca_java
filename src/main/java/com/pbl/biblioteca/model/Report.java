package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import javafx.util.Pair;

import java.util.ArrayList;

public class Report {

    public ArrayList<Pair<String, Integer>> getPopularBooksAllTime(){
        return DAO.getLoanDAO().getPopularBooksAllTime();
    }

    public Integer getTotalLoans(){
        return DAO.getLoanDAO().getTotalLoans();
    }

    public Integer getTotalOverdueLoans(){
        return DAO.getLoanDAO().getTotalOverdueLoans(null);
    }

    public Integer getTotalReserves(){
        return DAO.getBookReserveDAO().getTotalReserves();
    }

    public ArrayList<Loan> getAllLoansFromUser(String username){
        return DAO.getLoanDAO().getAllFromUser(username);
    }
}
