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

    public static ArrayList<Loan> getAllLoanHistory(){
        return DAO.getReportDAO().getAllLoanHistory();
    }

    public static ArrayList<User> getAllUserHistory(){
        return DAO.getReportDAO().getAllUserHistory();
    }

    public static  ArrayList<Book> getAllBookHistory(){
        return DAO.getReportDAO().getAllBookHistory();
    }

    public static ArrayList<BookReserve> getAllReserveHistory(){
        return DAO.getReportDAO().getAllReserveHistory();
    }

    public static ArrayList<Loan> getReaderLoanHistory(String username){
        return DAO.getReportDAO().getReaderLoanHistory(username);
    }

    public static ArrayList<BookReserve> getReaderReserveHistory(String username){
        return DAO.getReportDAO().getReaderReserveHistory(username);
    }

    public static ArrayList<Pair<String, Integer>> getPopularBooksAllTime(){
        return DAO.getReportDAO().getPopularBooksAllTime();
    }

    public static Integer getTotalReservesAllTime(){
        return DAO.getReportDAO().getAllReserveHistory().size();
    }

    public static Integer getTotalLoansAllTime(){
        return DAO.getReportDAO().getAllLoanHistory().size();
    }
}
