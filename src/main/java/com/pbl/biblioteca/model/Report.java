package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import javafx.util.Pair;

import java.util.ArrayList;

public class Report {

    public static ArrayList<Pair<String, Integer>> getPopularBooksToday(){
        return DAO.getLoanDAO().getPopularBooksToday();
    }

    public static Integer getTotalLoans(){
        return DAO.getLoanDAO().getTotalLoans();
    }

    public static Integer getTotalOverdueLoans(){
        return DAO.getLoanDAO().getTotalOverdueLoans(null);
    }

    public static Integer getTotalReserves(){
        return DAO.getBookReserveDAO().getTotalReserves();
    }

    public static ArrayList<Loan> getAllLoansFromUser(String username){
        return DAO.getLoanDAO().getAllFromUser(username);
    }
}
