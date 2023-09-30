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

        userId = 0;
        loanId = 0;
        bookId = 0;
        bookReserveId = 0;
    }

    public static void clearMemory(){
        userHM = new HashMap<>();
        loanHM = new HashMap<>();
        adminHM = new HashMap<>();
        librarianHM = new HashMap<>();
        operatorHM = new HashMap<>();
        bookHM = new HashMap<>();
        bookReserveHM = new HashMap<>();
        readerHM = new HashMap<>();

        userId = 0;
        loanId = 0;
        bookId = 0;
        bookReserveId = 0;
    }

    @SuppressWarnings("unchecked")
    protected static <V> HashMap<String, V> getAnySavedHashmap(String type) {
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
        }

        return new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    protected static void saveAnyObject(Object objectHM, String type) {
        switch (type){
            case "admin" -> adminHM = (HashMap<String, Admin>) objectHM;
            case "reader" -> readerHM = (HashMap<String, Reader>) objectHM;
            case "book" -> bookHM = (HashMap<String, Book>) objectHM;
            case "bookReserve" -> bookReserveHM = (HashMap<String, BookReserve>) objectHM;
            case "operator" -> operatorHM = (HashMap<String, Operator>) objectHM;
            case "librarian" -> librarianHM = (HashMap<String, Librarian>) objectHM;
            case "loan" -> loanHM = (HashMap<String, Loan>) objectHM;
        }

    }

    protected static String generateId(String type){
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
