package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.*;

import java.io.*;
import java.util.HashMap;


public class ConnectionDAO {

    protected static final String userFileUrl = "users.ser";
    protected static final String loanFileUrl = "loans.ser";
    protected static final String operatorFileUrl = "operators.ser";
    protected static final String bookFileUrl = "books.ser";
    protected static final String librarianFileUrl = "librarians.ser";


    protected static Object getAnySavedHashmap(String fileUrl){
        FileInputStream fileIn;
        ObjectInputStream objectIn;
        Object anyHashmap = new Object();

        try{
            fileIn = new FileInputStream(fileUrl);
            objectIn = new ObjectInputStream(fileIn);
            anyHashmap = objectIn.readObject();

            objectIn.close();

        } catch (IOException | ClassNotFoundException e){
            System.out.println(e.toString());;
        }

        return anyHashmap;
    }

    protected static boolean saveAnyHashmap(Object objectHM, String fileUrl){
        ObjectOutputStream objectOut;
        FileOutputStream fileOut;

        try{
            fileOut = new FileOutputStream(fileUrl);
            objectOut =  new ObjectOutputStream(fileOut);
            objectOut.writeObject(objectHM);
            objectOut.close();

        } catch (IOException e){
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, User> getUserHashmap(){
        File testFile = new File(userFileUrl);
        HashMap<String, User> userHM = new HashMap<>();

        if (!testFile.isFile()) {
            saveAnyHashmap(userHM, userFileUrl);
        } else {
            userHM = (HashMap<String, User>) getAnySavedHashmap(userFileUrl);
        }

        return userHM;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Loan> getLoanHashmap() {
        File testFile = new File(loanFileUrl);
        HashMap<String, Loan> loanHM = new HashMap<>();

        if (!testFile.isFile()) {
            saveAnyHashmap(loanHM, loanFileUrl);
        } else {
            loanHM = (HashMap<String, Loan>) getAnySavedHashmap(loanFileUrl);
        }

        return loanHM;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Operator> getOperatorHashmap(){
        File testFile = new File(operatorFileUrl);
        HashMap<String, Operator> operatorHM = new HashMap<>();

        if (!testFile.isFile()) {
            saveAnyHashmap(operatorHM, operatorFileUrl);
        } else {
            operatorHM = (HashMap<String, Operator>) getAnySavedHashmap(operatorFileUrl);
        }

        return operatorHM;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Book> getBookHashmap(){
        File testFile = new File(bookFileUrl);
        HashMap<String, Book> bookHM = new HashMap<>();

        if (!testFile.isFile()) {
            saveAnyHashmap(bookHM, bookFileUrl);
        } else {
            bookHM = (HashMap<String, Book>) getAnySavedHashmap(bookFileUrl);
        }

        return bookHM;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Librarian> getLibrarianHashmap(){
        File testFile = new File(librarianFileUrl);
        HashMap<String, Librarian> librarianHM = new HashMap<>();

        if (!testFile.isFile()) {
            saveAnyHashmap(librarianHM, librarianFileUrl);
        } else {
            librarianHM = (HashMap<String, Librarian>) getAnySavedHashmap(librarianFileUrl);
        }

        return librarianHM;
    }

}
