package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;

public class Report {

    /**
     * Pega todos os livros que estão emprestados atualmente, em ordem de popularidade
     * @return Retorna um Array de pairs, a key é o isbn do livro,
     *         o value é a quantidade de cópias desse livro sendo emprestadas atualmente
     */
    public static ArrayList<Pair<String, Integer>> getPopularBooksToday(){
        return DAO.getLoanDAO().getPopularBooksToday();
    }

    /**
     * Pega o total de empréstimos atuais
     * @return Retorna um inteiro com a quantidade de Laans
     */
    public static Integer getTotalActiveLoans(){
        return DAO.getLoanDAO().getTotalLoans();
    }

    /**
     * Pega um inteiro com a quantidade de empréstimos atrasados
     * @param  now Para fins de teste, é possível enviar uma data arbitrária,
     *             caso now = null, a data atual será utilizada
     * @return Retorna o inteiro correspondente a quantidade de Loans atrasados
     */
    public static Integer getTotalOverdueLoans(LocalDate now){
        return DAO.getLoanDAO().getTotalOverdueLoans(now);
    }

    /**
     * Pega o total de reservas ativas
     * @return Retorna um Inteiro com o total de reservas
     */
    public static Integer getTotalActiveReserves(){
        return DAO.getBookReserveDAO().getTotalReserves();
    }

    /**
     * Pega todos os empréstimos de um Reader
     * @param  username Primary key do Reader
     * @return Retorna um Array com todos os Loans
     */
    public static ArrayList<Loan> getUserActiveLoans(String username){
        return DAO.getLoanDAO().getAllFromUser(username);
    }

    /**
     * Pega o histórico com todos os empréstimos já criados
     * @return Retorna um ArrayList com todos os Loans
     */
    public static ArrayList<Loan> getAllLoanHistory(){
        return DAO.getReportDAO().getAllLoanHistory();
    }

    /**
     * Pega o histórico com todos os usuários já criados
     * @return Retorna um ArrayList com todos os Users
     */
    public static ArrayList<User> getAllUserHistory(){
        return DAO.getReportDAO().getAllUserHistory();
    }

    /**
     * Pega o histórico com todos os livros já criados
     * @return Retorna um ArrayList com todos os Books
     */
    public static  ArrayList<Book> getAllBookHistory(){
        return DAO.getReportDAO().getAllBookHistory();
    }

    /**
     * Pega o histórico com todas as reservas já criadas
     * @return Retorna um ArrayList com todos os BookReserves
     */
    public static ArrayList<BookReserve> getAllReserveHistory(){
        return DAO.getReportDAO().getAllReserveHistory();
    }

    /**
     * Pega o histórico com todos os empréstimos já criados por um Reader
     * @param  username O username é a primary key
     * @return Retorna um ArrayList com todos os Loans do Reader
     */
    public static ArrayList<Loan> getReaderLoanHistory(String username){
        return DAO.getReportDAO().getReaderLoanHistory(username);
    }

    /**
     * Pega o histórico com todas as reservas já criados por um Reader
     * @param  username O username é a primary key
     * @return Retorna um ArrayList com todos os BookReserves do Reader
     */
    public static ArrayList<BookReserve> getReaderReserveHistory(String username){
        return DAO.getReportDAO().getReaderReserveHistory(username);
    }

    /**
     * Pega o histórico com todos os livros, em ordem de popularidade
     * @return Retorna um ArrayList de Pairs, a key é o isbn do livro,
     * o value é a quantidade de empréstimos já feitos para esse livro
     */
    public static ArrayList<Pair<String, Integer>> getPopularBooksAllTime(){
        return DAO.getReportDAO().getPopularBooksAllTime();
    }

    /**
     * Pega o total de reservas já criadas
     * @return Retorna um Inteiro com o total de reservas
     */
    public static Integer getTotalReservesAllTime(){
        return DAO.getReportDAO().getAllReserveHistory().size();
    }

    /**
     * Pega o total de empréstimos já criados
     * @return Retorna um Inteiro com o total de empréstimos
     */
    public static Integer getTotalLoansAllTime(){
        return DAO.getReportDAO().getAllLoanHistory().size();
    }
}
