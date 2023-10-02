package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.*;
import com.pbl.biblioteca.model.Reader;


import java.util.HashMap;


public class ConnectionMemory {

    private static HashMap<String, User> userHM;
    private static HashMap<String, Loan> loanHM;
    private static HashMap<String, Operator> operatorHM;
    private static HashMap<String, Book> bookHM;
    private static HashMap<String, Librarian> librarianHM;
    private static HashMap<String, BookReserve> bookReserveHM;
    private static HashMap<String, Reader> readerHM;
    private static HashMap<String, Admin> adminHM;
    private static HashMap<String, Loan> loanLogHM;
    private static HashMap<String, User> userLogHM;
    private static HashMap<String, Book> bookLogHM;
    private static HashMap<String, BookReserve> reserveLogHM;

    private static Integer userId;
    private static Integer loanId;
    private static Integer bookId;
    private static Integer bookReserveId;


    public ConnectionMemory(){
        userHM = new HashMap<>();
        loanHM = new HashMap<>();
        adminHM = new HashMap<>();
        librarianHM = new HashMap<>();
        operatorHM = new HashMap<>();
        bookHM = new HashMap<>();
        bookReserveHM = new HashMap<>();
        readerHM = new HashMap<>();
        loanLogHM = new HashMap<>();
        userLogHM = new HashMap<>();
        bookLogHM = new HashMap<>();
        reserveLogHM = new HashMap<>();

        userId = 0;
        loanId = 0;
        bookId = 0;
        bookReserveId = 0;
    }

    /**
     * Limpa todos os dados salvos na memória
     */
    public static void clearMemory(){
        userHM = new HashMap<>();
        loanHM = new HashMap<>();
        adminHM = new HashMap<>();
        librarianHM = new HashMap<>();
        operatorHM = new HashMap<>();
        bookHM = new HashMap<>();
        bookReserveHM = new HashMap<>();
        readerHM = new HashMap<>();
        loanLogHM = new HashMap<>();
        userLogHM = new HashMap<>();
        bookLogHM = new HashMap<>();
        reserveLogHM = new HashMap<>();

        userId = 0;
        loanId = 0;
        bookId = 0;
        bookReserveId = 0;
    }

    /**
     * Pega o hashmap salvo de acordo com o tipo enviado
     * @param  type O tipo do value do hashmap a ser resgatado
     * @return Retorna o HashMap com a PK na key e o Objeto no value
     */
    @SuppressWarnings("unchecked")
    public static <V> HashMap<String, V> getAnySavedHashmap(String type) {
        switch (type){
            case "admin" -> {
                return (HashMap<String, V>) adminHM;
            }
            case "reader" -> {
                return (HashMap<String, V>) readerHM;
            }
            case "book" -> {
                return (HashMap<String, V>) bookHM;
            }
            case "bookReserve" -> {
                return (HashMap<String, V>) bookReserveHM;
            }
            case "operator" -> {
                return (HashMap<String, V>) operatorHM;
            }
            case "librarian" -> {
                return (HashMap<String, V>) librarianHM;
            }
            case "loan" -> {
                return (HashMap<String, V>) loanHM;
            }
            case "user" -> {
                return (HashMap<String, V>) userHM;
            }
            case "userlog" -> {
                return (HashMap<String, V>) userLogHM;
            }
            case "loanlog" -> {
                return (HashMap<String, V>) loanLogHM;
            }
            case "booklog" -> {
                return (HashMap<String, V>) bookLogHM;
            }
            case "reservelog" -> {
                return (HashMap<String, V>) reserveLogHM;
            }
        }

        return new HashMap<>();
    }

    /**
     * Salva um objeto qualquer de acordo com o tipo enviado
     * @param objectHM O objeto a ser salvo
     * @param type O tipo de objeto a ser salvo
     */
    @SuppressWarnings("unchecked")
    public static void saveAnyObject(Object objectHM, String type) {
        switch (type){
            case "admin" -> adminHM = (HashMap<String, Admin>) objectHM;
            case "reader" -> readerHM = (HashMap<String, Reader>) objectHM;
            case "book" -> bookHM = (HashMap<String, Book>) objectHM;
            case "bookReserve" -> bookReserveHM = (HashMap<String, BookReserve>) objectHM;
            case "operator" -> operatorHM = (HashMap<String, Operator>) objectHM;
            case "librarian" -> librarianHM = (HashMap<String, Librarian>) objectHM;
            case "loan" -> loanHM = (HashMap<String, Loan>) objectHM;
            case "loanlog" -> loanLogHM = (HashMap<String, Loan>) objectHM;
            case "booklog" -> bookLogHM = (HashMap<String, Book>) objectHM;
            case "userlog" -> userLogHM = (HashMap<String, User>) objectHM;
            case "reservelog" -> reserveLogHM = (HashMap<String, BookReserve>) objectHM;
        }

    }

    /**
     * Acessa o objeto que armazena os Ids, de acordo com o tipo
     * vê o valor salvo nesse objeto, adiciona 1, salva o objeto novamente
     * e retorna o Id no tipo String
     * @param  type O tipo do objeto que possui a Id
     * @return Retorna o Id num tipo String
     */
    public static String generateId(String type){
        switch (type){
            case "book" -> {
                bookId ++;
                return bookId.toString();
            }
            case "bookReserve" -> {
                bookReserveId ++;
                return bookReserveId.toString();
            }
            case "loan" -> {
                loanId ++;
                return loanId.toString();
            }
            case "user" -> {
                userId ++;
                return userId.toString();
            }
        }

        return null;
    }
}
