package com.pbl.biblioteca.dao.Report;


import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.BookReserve;
import com.pbl.biblioteca.model.Loan;
import com.pbl.biblioteca.model.User;
import javafx.util.Pair;

import java.util.ArrayList;

public interface ReportDAO {

    void logNewLoan(Loan loan);
    void logNewUser(User user);
    void logNewBook(Book book);
    void logNewReserve(BookReserve reserve);

    ArrayList<Loan> getAllLoanHistory();
    ArrayList<User> getAllUserHistory();
    ArrayList<Book> getAllBookHistory();
    ArrayList<BookReserve> getAllReserveHistory();

    ArrayList<Loan> getReaderLoanHistory(String username);
    ArrayList<BookReserve> getReaderReserveHistory(String username);

    ArrayList<Pair<String, Integer>> getPopularBooksAllTime();
}
