package com.pbl.biblioteca.DAO;

import com.pbl.biblioteca.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class ConnectionDAO {

    private static final String userFileUrl = "users.ser";
    private static final String loanFileUrl = "loans.ser";
    private static final String bookFileUrl = "books.ser";
    private static final String operatorFileUrl = "operators.ser";


    private static Object getAnySavedHashmap(String fileUrl){
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

    private static boolean saveAnyHashmap(Object objectHM, String fileUrl){
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

    public static boolean saveBook(Book bookObject){
        HashMap<String, Book> bookHM = getBookHashmap();
        if (bookHM == null){
            bookHM = new HashMap<String, Book>();
        }

        bookHM.put(bookObject.getIsbn(),bookObject);

        return saveAnyHashmap(bookHM, bookFileUrl);
    }

    public static boolean saveLoan(Loan loanObject){
        HashMap<String, Loan> loanHM = getLoanHashmap();
        if (loanHM == null){
            loanHM = new HashMap<String, Loan>();
        }

        loanHM.put(loanObject.getId(),loanObject);

        return saveAnyHashmap(loanHM, loanFileUrl);
    }

    public static boolean saveUser(User userObject){
        HashMap<String, User> userHM = getUserHashmap();
        if (userHM == null){
            userHM = new HashMap<String, User>();
        }

        userHM.put(userObject.getNickname(),userObject);

        return saveAnyHashmap(userHM, userFileUrl);
    }

    public static boolean saveOperator(Operator operatorObject){
        HashMap<String, Operator> operatorHM = getOperatorHashmap();
        if (operatorHM == null){
            operatorHM = new HashMap<String, Operator>();
        }

        operatorHM.put(operatorObject.getId(),operatorObject);

        return saveAnyHashmap(operatorHM, operatorFileUrl);
    }

}
