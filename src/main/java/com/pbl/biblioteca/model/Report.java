package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public class Report {

    public static ArrayList<Pair<String, Integer>> getPopularBooksToday(){
        return DAO.getLoanDAO().getPopularBooksToday();
    }

    public static Integer getTotalActiveLoans(){
        return DAO.getLoanDAO().getTotalLoans();
    }

    public static Integer getTotalOverdueLoans(LocalDate now){
        return DAO.getLoanDAO().getTotalOverdueLoans(now);
    }

    public static Integer getTotalActiveReserves(){
        return DAO.getBookReserveDAO().getTotalReserves();
    }

    public static ArrayList<Loan> getUserActiveLoans(String username){
        return DAO.getLoanDAO().getAllFromUser(username);
    }
}
