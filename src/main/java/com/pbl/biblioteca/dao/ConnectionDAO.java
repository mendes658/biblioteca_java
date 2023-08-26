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
    protected static  String bookCopiesUrl;
    protected static  String totalLoansByBookUrl;
    protected static  String bookReserveUrl;

    protected static  String defaultUserFileUrl = "users.ser";
    protected static  String defaultLoanFileUrl = "loans.ser";
    protected static  String defaultOperatorFileUrl = "operators.ser";
    protected static  String defaultBookFileUrl = "books.ser";
    protected static  String defaultLibrarianFileUrl = "librarians.ser";
    protected static  String defaultBookCopiesUrl = "books_copies.ser";
    protected static  String defaultTotalLoansByBookUrl = "total_loans.ser";
    protected static  String defaultBookReserveUrl = "book_reserves.ser";

    public static void setTestFileUrls() {
        ConnectionDAO.userFileUrl = "test_" + defaultUserFileUrl;
        ConnectionDAO.loanFileUrl = "test_" + defaultLoanFileUrl;
        ConnectionDAO.bookFileUrl = "test_" + defaultBookFileUrl;
        ConnectionDAO.operatorFileUrl = "test_" + defaultOperatorFileUrl;
        ConnectionDAO.librarianFileUrl = "test_" + defaultLibrarianFileUrl;
        ConnectionDAO.bookCopiesUrl = "test_" + defaultBookCopiesUrl;
        ConnectionDAO.totalLoansByBookUrl = "test_" + defaultTotalLoansByBookUrl;
        ConnectionDAO.bookReserveUrl = "test_" + defaultBookReserveUrl;

    }

    public static void setDefaultFileUrls() {
        ConnectionDAO.userFileUrl = defaultUserFileUrl;
        ConnectionDAO.librarianFileUrl = defaultLibrarianFileUrl;
        ConnectionDAO.operatorFileUrl = defaultOperatorFileUrl;
        ConnectionDAO.loanFileUrl = defaultLoanFileUrl;
        ConnectionDAO.bookFileUrl = defaultBookFileUrl;
        ConnectionDAO.bookCopiesUrl = defaultBookCopiesUrl;
        ConnectionDAO.totalLoansByBookUrl = defaultTotalLoansByBookUrl;
        ConnectionDAO.bookReserveUrl = defaultBookReserveUrl;
    }

    public static void cleanTestFiles(){

        saveAnyObject(new HashMap<String, User>() ,"test_" + defaultUserFileUrl);
        saveAnyObject(new HashMap<String, Librarian>(),"test_" + defaultLibrarianFileUrl);
        saveAnyObject(new HashMap<String, Operator>(),"test_" + defaultOperatorFileUrl);
        saveAnyObject(new HashMap<String, Loan>(),"test_" + defaultLoanFileUrl);
        saveAnyObject(new HashMap<String, Book>(),"test_" + defaultBookFileUrl);
        saveAnyObject(new HashMap<String, BookCopy>(),"test_" + defaultBookCopiesUrl);
        saveAnyObject(new HashMap<String, Integer>(),"test_" + defaultTotalLoansByBookUrl);
        saveAnyObject(new HashMap<String, BookReserve>(),"test_" + defaultBookReserveUrl);

    }


    @SuppressWarnings("unchecked")
    protected static <V> HashMap<String, V> getAnySavedHashmap(String fileUrl) {
        FileInputStream fileIn;
        ObjectInputStream objectIn;
        File testFile = new File(fileUrl);
        HashMap<String, V> anyHashmap = new HashMap<>();

        if (!testFile.isFile()) {
            saveAnyObject(anyHashmap, fileUrl);
        }

        try {
            fileIn = new FileInputStream(fileUrl);
            objectIn = new ObjectInputStream(fileIn);
            anyHashmap = (HashMap<String, V>) objectIn.readObject();

            objectIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return anyHashmap;
    }


    protected static boolean saveAnyObject(Object objectHM, String fileUrl) {
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

    protected static String generateId(String idUrl){
        idUrl = "id_" + idUrl;

        FileInputStream fileIn;
        ObjectInputStream objectIn;
        File testFile = new File(idUrl);
        Integer id = 0;

        if (!testFile.isFile()) {
            saveAnyObject(id, idUrl);
            return id.toString();
        }

        try {
            fileIn = new FileInputStream(idUrl);
            objectIn = new ObjectInputStream(fileIn);
            id = (Integer) objectIn.readObject();
            id++;
            saveAnyObject(id, idUrl);

            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return id.toString();
    }
}
