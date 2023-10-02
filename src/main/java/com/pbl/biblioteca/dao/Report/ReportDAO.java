package com.pbl.biblioteca.dao.Report;


import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.BookReserve;
import com.pbl.biblioteca.model.Loan;
import com.pbl.biblioteca.model.User;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public interface ReportDAO {

    /**
     * Salva um log da criação de um Loan
     * @param  loan O Loan a ser salvo
     */
    void logNewLoan(Loan loan);

    /**
     * Salva um log da criação de um User
     * @param  user O User a ser salvo
     */
    void logNewUser(User user);

    /**
     * Salva um log da criação de um Book
     * @param  book O Book a ser salvo
     */
    void logNewBook(Book book);

    /**
     * Salva um log da criação de um BookReserve
     * @param  reserve O BookReserve a ser salvo
     */
    void logNewReserve(BookReserve reserve);

    /**
     * Pega o histórico com todos os empréstimos já criados
     * @return Retorna um ArrayList com todos os Loans
     */
    ArrayList<Loan> getAllLoanHistory();

    /**
     * Pega o histórico com todos os usuários já criados
     * @return Retorna um ArrayList com todos os Users
     */
    ArrayList<User> getAllUserHistory();

    /**
     * Pega o histórico com todos os livros já criados
     * @return Retorna um ArrayList com todos os Books
     */
    ArrayList<Book> getAllBookHistory();

    /**
     * Pega o histórico com todas as reservas já criadas
     * @return Retorna um ArrayList com todos os BookReserves
     */
    ArrayList<BookReserve> getAllReserveHistory();

    /**
     * Pega o histórico com todos os empréstimos já criados por um Reader
     * @param  username O username é a primary key
     * @return Retorna um ArrayList com todos os Loans do Reader
     */
    ArrayList<Loan> getReaderLoanHistory(String username);

    /**
     * Pega o histórico com todas as reservas já criados por um Reader
     * @param  username O username é a primary key
     * @return Retorna um ArrayList com todos os BookReserves do Reader
     */
    ArrayList<BookReserve> getReaderReserveHistory(String username);

    /**
     * Pega o histórico com todos os livros, em ordem de popularidade
     * @return Retorna um ArrayList de Pairs, a key é o isbn do livro,
     * o value é a quantidade de empréstimos já feitos para esse livro
     */
    ArrayList<Pair<String, Integer>> getPopularBooksAllTime();
}
