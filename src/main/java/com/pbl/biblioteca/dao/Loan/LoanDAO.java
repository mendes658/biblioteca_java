package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.CRUD;
import com.pbl.biblioteca.model.Loan;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public interface LoanDAO extends CRUD<Loan> {

    /**
     * Gera um novo id
     * @return Retorna o id em uma String
     */
    String generateId();

    /**
     * Pega todos os livros que estão emprestados atualmente, em ordem de popularidade
     * @return Retorna um Array de pairs, a key é o isbn do livro,
     *         o value é a quantidade de cópias desse livro sendo emprestadas atualmente
     */
    ArrayList<Pair<String, Integer>> getPopularBooksToday();

    /**
     * Pega o total de empréstimos atuais
     * @return Retorna um inteiro com a quantidade de Laans
     */
    Integer getTotalLoans();

    /**
     * Pega um inteiro com a quantidade de empréstimos atrasados
     * @param  now Para fins de teste, é possível enviar uma data arbitrária,
     *             caso now = null, a data atual será utilizada
     * @return Retorna o inteiro correspondente a quantidade de Loans atrasados
     */
    Integer getTotalOverdueLoans(LocalDate now);

    /**
     * Pega todos os empréstimos de um Reader
     * @param  username Primary key do Reader
     * @return Retorna um Array com todos os Loans
     */
    ArrayList<Loan> getAllFromUser(String username);

    /**
     * Pega todos os empréstimos de um livro
     * @param  isbn Primary key do livro
     * @return Retorna um Array com todos os Loans
     */
    ArrayList<Loan> getAllFromBook(String isbn);
}
