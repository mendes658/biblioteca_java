package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class ConnectionDAO {

    protected static  String userFileUrl;
    protected static  String loanFileUrl;
    protected static  String operatorFileUrl;
    protected static  String bookFileUrl;
    protected static  String librarianFileUrl;
    protected static  String booksIsbnsByCategoryUrl;

    protected static  String defaultUserFileUrl = "users.ser";
    protected static  String defaultLoanFileUrl = "loans.ser";
    protected static  String defaultOperatorFileUrl = "operators.ser";
    protected static  String defaultBookFileUrl = "books.ser";
    protected static  String defaultLibrarianFileUrl = "librarians.ser";
    protected static  String defaultBooksIsbnsByCategoryUrl = "books_ids_category.ser";

    public static void setTestFileUrls() {
        ConnectionDAO.userFileUrl = "test_" + defaultUserFileUrl;
        ConnectionDAO.loanFileUrl = "test_" + defaultLoanFileUrl;
        ConnectionDAO.bookFileUrl = "test_" + defaultBookFileUrl;
        ConnectionDAO.operatorFileUrl = "test_" + defaultOperatorFileUrl;
        ConnectionDAO.librarianFileUrl = "test_" + defaultLibrarianFileUrl;
        ConnectionDAO.booksIsbnsByCategoryUrl = "test_" + defaultBooksIsbnsByCategoryUrl;
    }

    public static void setDefaultFileUrls() {
        ConnectionDAO.userFileUrl = defaultUserFileUrl;
        ConnectionDAO.librarianFileUrl = defaultLibrarianFileUrl;
        ConnectionDAO.operatorFileUrl = defaultOperatorFileUrl;
        ConnectionDAO.loanFileUrl = defaultLoanFileUrl;
        ConnectionDAO.booksIsbnsByCategoryUrl = defaultBooksIsbnsByCategoryUrl;
        ConnectionDAO.bookFileUrl = defaultBookFileUrl;
    }

    public static void cleanTestFiles(){

        saveAnyHashmap(new HashMap<String, User>() ,"test_" + defaultUserFileUrl);
        saveAnyHashmap(new HashMap<String, Librarian>(),"test_" + defaultLibrarianFileUrl);
        saveAnyHashmap(new HashMap<String, Operator>(),"test_" + defaultOperatorFileUrl);
        saveAnyHashmap(new HashMap<String, Loan>(),"test_" + defaultLoanFileUrl);
        saveAnyHashmap(new HashMap<String, ArrayList<String>>(),"test_" + defaultBooksIsbnsByCategoryUrl);
        saveAnyHashmap(new HashMap<String, Book>(),"test_" + defaultBookFileUrl);

    }


    @SuppressWarnings("unchecked")
    protected static <V> HashMap<String, V> getAnySavedHashmap(String fileUrl) {
        FileInputStream fileIn;
        ObjectInputStream objectIn;
        File testFile = new File(fileUrl);
        HashMap<String, V> anyHashmap = new HashMap<>();

        if (!testFile.isFile()) {
            saveAnyHashmap(anyHashmap, fileUrl);
        }

        try {
            fileIn = new FileInputStream(fileUrl);
            objectIn = new ObjectInputStream(fileIn);
            anyHashmap = (HashMap<String, V>) objectIn.readObject();

            objectIn.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return anyHashmap;
    }


    protected static boolean saveAnyHashmap(Object objectHM, String fileUrl) {
        ObjectOutputStream objectOut;
        FileOutputStream fileOut;

        try {
            fileOut = new FileOutputStream(fileUrl);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(objectHM);
            objectOut.close();

        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
