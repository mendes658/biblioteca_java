package com.pbl.biblioteca.dao.Report;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.BookReserve;
import com.pbl.biblioteca.model.Loan;
import com.pbl.biblioteca.model.User;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ReportMemoryImpl extends ConnectionMemory implements ReportDAO{


    /**
     * Salva um log da criação de um Loan
     * @param  loan O Loan a ser salvo
     */
    @Override
    public void logNewLoan(Loan loan) {
        HashMap<String, Loan> loanLogHM = getAnySavedHashmap("loanlog");
        loanLogHM.put(loan.getId(), loan);

        saveAnyObject(loanLogHM, "loanlog");
    }

    /**
     * Salva um log da criação de um User
     * @param  user O User a ser salvo
     */
    @Override
    public void logNewUser(User user) {
        HashMap<String, User> userLogHM = getAnySavedHashmap("userlog");
        userLogHM.put(user.getId(), user);

        saveAnyObject(userLogHM, "userlog");
    }

    /**
     * Salva um log da criação de um Book
     * @param  book O Book a ser salvo
     */
    @Override
    public void logNewBook(Book book) {
        HashMap<String, Book> bookLogHM = getAnySavedHashmap("booklog");
        bookLogHM.put(book.getId(), book);

        saveAnyObject(bookLogHM, "booklog");
    }

    /**
     * Salva um log da criação de um BookReserve
     * @param  reserve O BookReserve a ser salvo
     */
    @Override
    public void logNewReserve(BookReserve reserve) {
        HashMap<String, BookReserve> reserveLogHM = getAnySavedHashmap("reservelog");
        reserveLogHM.put(reserve.getId(), reserve);

        saveAnyObject(reserveLogHM, "reservelog");
    }

    /**
     * Pega o histórico com todos os empréstimos já criados
     * @return Retorna um ArrayList com todos os Loans
     */
    @Override
    public ArrayList<Loan> getAllLoanHistory() {
        HashMap<String, Loan> allLoanHM = getAnySavedHashmap("loanlog");
        ArrayList<Loan> allLoan = new ArrayList<>();

        for (String key : allLoanHM.keySet()){
            allLoan.add(allLoanHM.get(key));
        }

        return allLoan;
    }

    /**
     * Pega o histórico com todos os usuários já criados
     * @return Retorna um ArrayList com todos os Users
     */
    @Override
    public ArrayList<User> getAllUserHistory() {
        HashMap<String, User> allUserHM = getAnySavedHashmap("userlog");
        ArrayList<User> allUser = new ArrayList<>();

        for (String key : allUserHM.keySet()){
            allUser.add(allUserHM.get(key));
        }

        return allUser;
    }

    /**
     * Pega o histórico com todos os livros já criados
     * @return Retorna um ArrayList com todos os Books
     */
    @Override
    public ArrayList<Book> getAllBookHistory() {
        HashMap<String, Book> allBookHM = getAnySavedHashmap("booklog");
        ArrayList<Book> allBook = new ArrayList<>();

        for (String key : allBookHM.keySet()){
            allBook.add(allBookHM.get(key));
        }

        return allBook;
    }

    /**
     * Pega o histórico com todas as reservas já criadas
     * @return Retorna um ArrayList com todos os BookReserves
     */
    @Override
    public ArrayList<BookReserve> getAllReserveHistory() {
        HashMap<String, BookReserve> allBookReserveHM = getAnySavedHashmap("reservelog");
        ArrayList<BookReserve> allBookReserve = new ArrayList<>();

        for (String key : allBookReserveHM.keySet()){
            allBookReserve.add(allBookReserveHM.get(key));
        }

        return allBookReserve;
    }

    /**
     * Pega o histórico com todos os empréstimos já criados por um Reader
     * @param  username O username é a primary key
     * @return Retorna um ArrayList com todos os Loans do Reader
     */
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

    /**
     * Pega o histórico com todas as reservas já criados por um Reader
     * @param  username O username é a primary key
     * @return Retorna um ArrayList com todos os BookReserves do Reader
     */
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

    /**
     * Pega o histórico com todos os livros, em ordem de popularidade
     * @return Retorna um ArrayList de Pairs, a key é o isbn do livro,
     * o value é a quantidade de empréstimos já feitos para esse livro
     */
    @Override
    public ArrayList<Pair<String, Integer>> getPopularBooksAllTime() {
        HashMap<String, Integer> totalLoansHM = new HashMap<>();
        HashMap<String, Loan> loanHM = getAnySavedHashmap("loanlog");

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
