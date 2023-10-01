package com.pbl.biblioteca.dao.Report;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.BookReserve;
import com.pbl.biblioteca.model.Loan;
import com.pbl.biblioteca.model.User;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ReportFileImpl extends ConnectionFile implements ReportDAO{


    @Override
    public void logNewLoan(Loan loan) {
        HashMap<String, Loan> loanLogHM = getAnySavedHashmap(loanLogUrl);
        loanLogHM.put(loan.getId(), loan);

        saveAnyObject(loanLogHM, loanLogUrl);
    }

    @Override
    public void logNewUser(User user) {
        HashMap<String, User> userLogHM = getAnySavedHashmap(userLogUrl);
        userLogHM.put(user.getId(), user);

        saveAnyObject(userLogHM, userLogUrl);
    }

    @Override
    public void logNewBook(Book book) {
        HashMap<String, Book> bookLogHM = getAnySavedHashmap(bookLogUrl);
        bookLogHM.put(book.getId(), book);

        saveAnyObject(bookLogHM, bookLogUrl);
    }

    @Override
    public void logNewReserve(BookReserve reserve) {
        HashMap<String, BookReserve> reserveLogHM = getAnySavedHashmap(reserveLogUrl);
        reserveLogHM.put(reserve.getId(), reserve);

        saveAnyObject(reserveLogHM, reserveLogUrl);
    }

    @Override
    public ArrayList<Loan> getAllLoanHistory() {
        HashMap<String, Loan> allLoanHM = getAnySavedHashmap(loanLogUrl);
        ArrayList<Loan> allLoan = new ArrayList<>();

        for (String key : allLoanHM.keySet()){
            allLoan.add(allLoanHM.get(key));
        }

        return allLoan;
    }

    @Override
    public ArrayList<User> getAllUserHistory() {
        HashMap<String, User> allUserHM = getAnySavedHashmap(userLogUrl);
        ArrayList<User> allUser = new ArrayList<>();

        for (String key : allUserHM.keySet()){
            allUser.add(allUserHM.get(key));
        }

        return allUser;
    }

    @Override
    public ArrayList<Book> getAllBookHistory() {
        HashMap<String, Book> allBookHM = getAnySavedHashmap(bookLogUrl);
        ArrayList<Book> allBook = new ArrayList<>();

        for (String key : allBookHM.keySet()){
            allBook.add(allBookHM.get(key));
        }

        return allBook;
    }

    @Override
    public ArrayList<BookReserve> getAllReserveHistory() {
        HashMap<String, BookReserve> allBookReserveHM = getAnySavedHashmap(reserveLogUrl);
        ArrayList<BookReserve> allBookReserve = new ArrayList<>();

        for (String key : allBookReserveHM.keySet()){
            allBookReserve.add(allBookReserveHM.get(key));
        }

        return allBookReserve;
    }

    @Override
    public ArrayList<Loan> getReaderLoanHistory(String username) {
        HashMap<String, Loan> allLoans = getAnySavedHashmap(loanLogUrl);
        ArrayList<Loan> fromUser = new ArrayList<>();

        for (String key : allLoans.keySet()){
            if (allLoans.get(key).getUsername().equals(username)){
                fromUser.add(allLoans.get(key));
            }
        }

        return fromUser;
    }

    @Override
    public ArrayList<BookReserve> getReaderReserveHistory(String username) {
        HashMap<String, BookReserve> allReservesHM = getAnySavedHashmap(reserveLogUrl);
        ArrayList<BookReserve> fromUser = new ArrayList<>();

        for (String key : allReservesHM.keySet()){
            if (allReservesHM.get(key).getUsername().equals(username)){
                fromUser.add(allReservesHM.get(key));
            }
        }

        return fromUser;
    }

    @Override
    public ArrayList<Pair<String, Integer>> getPopularBooksAllTime() {
        HashMap<String, Integer> totalLoansHM = new HashMap<>();
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanLogUrl);

        String newKey;

        for (String key : loanHM.keySet()){
            newKey = loanHM.get(key).getBookIsbn();

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
