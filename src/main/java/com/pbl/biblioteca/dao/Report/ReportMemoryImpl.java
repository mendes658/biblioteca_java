package com.pbl.biblioteca.dao.Report;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.BookReserve;
import com.pbl.biblioteca.model.Loan;
import com.pbl.biblioteca.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportMemoryImpl extends ConnectionMemory implements ReportDAO{


    @Override
    public void logNewLoan(Loan loan) {
        HashMap<String, Loan> loanLogHM = getAnySavedHashmap("loanlog");
        loanLogHM.put(loan.getId(), loan);

        saveAnyObject(loanLogHM, "loanlog");
    }

    @Override
    public void logNewUser(User user) {
        HashMap<String, User> userLogHM = getAnySavedHashmap("userlog");
        userLogHM.put(user.getId(), user);

        saveAnyObject(userLogHM, "userlog");
    }

    @Override
    public void logNewBook(Book book) {
        HashMap<String, Book> bookLogHM = getAnySavedHashmap("booklog");
        bookLogHM.put(book.getId(), book);

        saveAnyObject(bookLogHM, "booklog");
    }

    @Override
    public void logNewReserve(BookReserve reserve) {
        HashMap<String, BookReserve> reserveLogHM = getAnySavedHashmap("reservelog");
        reserveLogHM.put(reserve.getId(), reserve);

        saveAnyObject(reserveLogHM, "reservelog");
    }

    @Override
    public ArrayList<Loan> getAllLoanHistory() {
        HashMap<String, Loan> allLoanHM = getAnySavedHashmap("loanlog");
        ArrayList<Loan> allLoan = new ArrayList<>();

        for (String key : allLoanHM.keySet()){
            allLoan.add(allLoanHM.get(key));
        }

        return allLoan;
    }

    @Override
    public ArrayList<User> getAllUserHistory() {
        HashMap<String, User> allUserHM = getAnySavedHashmap("userlog");
        ArrayList<User> allUser = new ArrayList<>();

        for (String key : allUserHM.keySet()){
            allUser.add(allUserHM.get(key));
        }

        return allUser;
    }

    @Override
    public ArrayList<Book> getAllBookHistory() {
        HashMap<String, Book> allBookHM = getAnySavedHashmap("booklog");
        ArrayList<Book> allBook = new ArrayList<>();

        for (String key : allBookHM.keySet()){
            allBook.add(allBookHM.get(key));
        }

        return allBook;
    }

    @Override
    public ArrayList<BookReserve> getAllReserveHistory() {
        HashMap<String, BookReserve> allBookReserveHM = getAnySavedHashmap("reservelog");
        ArrayList<BookReserve> allBookReserve = new ArrayList<>();

        for (String key : allBookReserveHM.keySet()){
            allBookReserve.add(allBookReserveHM.get(key));
        }

        return allBookReserve;
    }

    @Override
    public ArrayList<Loan> getReaderLoanHistory(String username) {
        HashMap<String, Loan> allLoans = getAnySavedHashmap("loanlog");
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
        HashMap<String, BookReserve> allReservesHM = getAnySavedHashmap("reservelog");
        ArrayList<BookReserve> fromUser = new ArrayList<>();

        for (String key : allReservesHM.keySet()){
            if (allReservesHM.get(key).getUsername().equals(username)){
                fromUser.add(allReservesHM.get(key));
            }
        }

        return fromUser;
    }
}
