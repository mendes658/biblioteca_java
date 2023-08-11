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
        Object anyHashmap;

        try{
            fileIn = new FileInputStream(userFileUrl);
            objectIn = new ObjectInputStream(fileIn);
            anyHashmap = objectIn.readObject();

            objectIn.close();

        } catch (IOException | ClassNotFoundException e){
            return null;
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
        return (HashMap<String, User>) getAnySavedHashmap(userFileUrl);
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Loan> getLoanHashmap(){
        return (HashMap<String, Loan>) getAnySavedHashmap(loanFileUrl);
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Operator> getOperatorHashmap(){
        return (HashMap<String, Operator>) getAnySavedHashmap(operatorFileUrl);
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Book> getBookHashmap(){
        return (HashMap<String, Book>) getAnySavedHashmap(bookFileUrl);
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Librarian> getLibrarianHashmap(){
        return (HashMap<String, Librarian>) getAnySavedHashmap(librarianFileUrl);
    }

}
