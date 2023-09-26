package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.*;
import com.pbl.biblioteca.model.Reader;

import java.io.*;
import java.util.HashMap;


public class ConnectionFile {

    protected static  String userFileUrl;
    protected static  String loanFileUrl;
    protected static  String operatorFileUrl;
    protected static  String bookFileUrl;
    protected static  String librarianFileUrl;
    protected static  String bookReserveUrl;
    protected static  String readerUrl;
    protected static  String adminUrl;

    private static final String BASE_FOLDER = "./storage";

    protected static  String defaultUserFileUrl = "users.ser";
    protected static  String defaultLoanFileUrl = "loans.ser";
    protected static  String defaultOperatorFileUrl = "operators.ser";
    protected static  String defaultBookFileUrl = "books.ser";
    protected static  String defaultLibrarianFileUrl = "librarians.ser";
    protected static  String defaultBookReserveUrl = "book_reserves.ser";
    protected static  String defaultReaderUrl = "reader.ser";
    protected static  String defaultAdminUrl = "admin.ser";


    public ConnectionFile(){
        new File (BASE_FOLDER).mkdirs();
        new File (BASE_FOLDER + "/ids/storage").mkdirs();
    }

    public static void setTestFileUrls() {
        userFileUrl = BASE_FOLDER + "/test_" + defaultUserFileUrl;
        loanFileUrl = BASE_FOLDER + "/test_" + defaultLoanFileUrl;
        bookFileUrl = BASE_FOLDER + "/test_" + defaultBookFileUrl;
        operatorFileUrl = BASE_FOLDER + "/test_" + defaultOperatorFileUrl;
        librarianFileUrl = BASE_FOLDER + "/test_" + defaultLibrarianFileUrl;
        bookReserveUrl = BASE_FOLDER + "/test_" + defaultBookReserveUrl;
        readerUrl = BASE_FOLDER + "/test_" + defaultReaderUrl;
        adminUrl = BASE_FOLDER + "/test_" + defaultAdminUrl;


    }

    public static void setDefaultFileUrls() {
        userFileUrl = BASE_FOLDER + "/" + defaultUserFileUrl;
        librarianFileUrl = BASE_FOLDER + "/" + defaultLibrarianFileUrl;
        operatorFileUrl = BASE_FOLDER + "/" + defaultOperatorFileUrl;
        loanFileUrl = BASE_FOLDER + "/" + defaultLoanFileUrl;
        bookFileUrl = BASE_FOLDER + "/" + defaultBookFileUrl;
        bookReserveUrl = BASE_FOLDER + "/" + defaultBookReserveUrl;
        readerUrl = BASE_FOLDER + "/" + defaultReaderUrl;
        adminUrl = BASE_FOLDER + "/" + defaultAdminUrl;
    }

    public static void clearTestFiles(){

        saveAnyObject(new HashMap<String, Reader>() ,BASE_FOLDER + "/test_" + defaultUserFileUrl);
        saveAnyObject(new HashMap<String, Librarian>(),BASE_FOLDER + "/test_" + defaultLibrarianFileUrl);
        saveAnyObject(new HashMap<String, Operator>(),BASE_FOLDER + "/test_" + defaultOperatorFileUrl);
        saveAnyObject(new HashMap<String, Loan>(),BASE_FOLDER + "/test_" + defaultLoanFileUrl);
        saveAnyObject(new HashMap<String, Book>(),BASE_FOLDER + "/test_" + defaultBookFileUrl);
        saveAnyObject(new HashMap<String, BookReserve>(),BASE_FOLDER + "/test_" + defaultBookReserveUrl);
        saveAnyObject(new HashMap<String, Reader>(), BASE_FOLDER + "/test_" + defaultReaderUrl);
        saveAnyObject(new HashMap<String, Admin>(), BASE_FOLDER + "/test_" + defaultAdminUrl);

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


    protected static void saveAnyObject(Object objectHM, String fileUrl) {
        ObjectOutputStream objectOut;
        FileOutputStream fileOut;

        try {
            fileOut = new FileOutputStream(fileUrl);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(objectHM);
            objectOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected static String generateId(String idUrl){
        idUrl = BASE_FOLDER + "/ids" + idUrl;

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
