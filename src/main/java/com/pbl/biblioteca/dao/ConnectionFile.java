package com.pbl.biblioteca.dao;

import com.pbl.biblioteca.model.*;
import com.pbl.biblioteca.model.Reader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class ConnectionFile {

    protected static  String userFileUrl;
    protected static  String loanFileUrl;
    protected static  String operatorFileUrl;
    protected static  String bookFileUrl;
    protected static  String librarianFileUrl;
    protected static  String bookReserveUrl;
    protected static  String readerUrl;
    protected static  String adminUrl;
    protected static  String loanLogUrl;
    protected static  String userLogUrl;
    protected static  String bookLogUrl;
    protected static  String reserveLogUrl;

    private static final String BASE_FOLDER = "./storage";

    protected static  String defaultUserFileUrl = "users.ser";
    protected static  String defaultLoanFileUrl = "loans.ser";
    protected static  String defaultOperatorFileUrl = "operators.ser";
    protected static  String defaultBookFileUrl = "books.ser";
    protected static  String defaultLibrarianFileUrl = "librarians.ser";
    protected static  String defaultBookReserveUrl = "book_reserves.ser";
    protected static  String defaultReaderUrl = "reader.ser";
    protected static  String defaultAdminUrl = "admin.ser";
    protected static  String defaultLoanLogUrl = "loan_log.ser";
    protected static  String defaultUserLogUrl = "user_log.ser";
    protected static  String defaultBookLogUrl = "book_log.ser";
    protected static  String defaultReserveLogUrl = "reserve_log.ser";


    public ConnectionFile(){
        new File (BASE_FOLDER).mkdirs();
        new File (BASE_FOLDER + "/ids/storage").mkdirs();
    }

    /**
     * Troca as URLs padrão para as urls de teste, para que seja possível
     * testar o programa sem apagar os dados reais inseridos no armazenamento
     */
    public static void setTestFileUrls() {
        new File (BASE_FOLDER).mkdirs();
        new File (BASE_FOLDER + "/ids/storage").mkdirs();

        userFileUrl = BASE_FOLDER + "/test_" + defaultUserFileUrl;
        loanFileUrl = BASE_FOLDER + "/test_" + defaultLoanFileUrl;
        bookFileUrl = BASE_FOLDER + "/test_" + defaultBookFileUrl;
        operatorFileUrl = BASE_FOLDER + "/test_" + defaultOperatorFileUrl;
        librarianFileUrl = BASE_FOLDER + "/test_" + defaultLibrarianFileUrl;
        bookReserveUrl = BASE_FOLDER + "/test_" + defaultBookReserveUrl;
        readerUrl = BASE_FOLDER + "/test_" + defaultReaderUrl;
        adminUrl = BASE_FOLDER + "/test_" + defaultAdminUrl;
        loanLogUrl = BASE_FOLDER + "/test_" + defaultLoanLogUrl;
        userLogUrl = BASE_FOLDER + "/test_" + defaultUserLogUrl;
        bookLogUrl = BASE_FOLDER + "/test_" + defaultBookLogUrl;
        reserveLogUrl = BASE_FOLDER + "/test_" + defaultReserveLogUrl;
    }

    /**
     * Coloca o valor padrão nas urls
     */
    public static void setDefaultFileUrls() {
        new File (BASE_FOLDER).mkdirs();
        new File (BASE_FOLDER + "/ids/storage").mkdirs();

        userFileUrl = BASE_FOLDER + "/" + defaultUserFileUrl;
        librarianFileUrl = BASE_FOLDER + "/" + defaultLibrarianFileUrl;
        operatorFileUrl = BASE_FOLDER + "/" + defaultOperatorFileUrl;
        loanFileUrl = BASE_FOLDER + "/" + defaultLoanFileUrl;
        bookFileUrl = BASE_FOLDER + "/" + defaultBookFileUrl;
        bookReserveUrl = BASE_FOLDER + "/" + defaultBookReserveUrl;
        readerUrl = BASE_FOLDER + "/" + defaultReaderUrl;
        adminUrl = BASE_FOLDER + "/" + defaultAdminUrl;
        loanLogUrl = BASE_FOLDER + "/" + defaultLoanLogUrl;
        userLogUrl = BASE_FOLDER + "/" + defaultUserLogUrl;
        bookLogUrl = BASE_FOLDER + "/" + defaultBookLogUrl;
        reserveLogUrl = BASE_FOLDER + "/" + defaultReserveLogUrl;
    }

    /**
     * Limpa os arquivos de teste
     */
    public static void clearTestFiles(){
        new File (BASE_FOLDER).mkdirs();
        new File (BASE_FOLDER + "/ids/storage").mkdirs();

        saveAnyObject(new HashMap<String, Reader>() ,BASE_FOLDER + "/test_" + defaultUserFileUrl);
        saveAnyObject(new HashMap<String, Librarian>(),BASE_FOLDER + "/test_" + defaultLibrarianFileUrl);
        saveAnyObject(new HashMap<String, Operator>(),BASE_FOLDER + "/test_" + defaultOperatorFileUrl);
        saveAnyObject(new HashMap<String, Loan>(),BASE_FOLDER + "/test_" + defaultLoanFileUrl);
        saveAnyObject(new HashMap<String, Book>(),BASE_FOLDER + "/test_" + defaultBookFileUrl);
        saveAnyObject(new HashMap<String, BookReserve>(),BASE_FOLDER + "/test_" + defaultBookReserveUrl);
        saveAnyObject(new HashMap<String, Reader>(), BASE_FOLDER + "/test_" + defaultReaderUrl);
        saveAnyObject(new HashMap<String, Admin>(), BASE_FOLDER + "/test_" + defaultAdminUrl);
        saveAnyObject(new HashMap<String, Loan>(), BASE_FOLDER + "/test_" + defaultLoanLogUrl);
        saveAnyObject(new HashMap<String, User>(), BASE_FOLDER + "/test_" + defaultUserLogUrl);
        saveAnyObject(new HashMap<String, Book>(), BASE_FOLDER + "/test_" + defaultBookLogUrl);
        saveAnyObject(new HashMap<String, BookReserve>(), BASE_FOLDER + "/test_" + defaultReserveLogUrl);

    }


    /**
     * Pega o hashmap salvo na url enviada, caso não exista,
     * um novo hashmap é criad e salvo
     * @param  fileUrl A url do hashmap salvo
     * @return Retorna o HashMap com a PK na key e o Objeto no value
     */
    @SuppressWarnings("unchecked")
    protected static <V> HashMap<String, V> getAnySavedHashmap(String fileUrl) {
        new File (BASE_FOLDER).mkdirs();
        new File (BASE_FOLDER + "/ids/storage").mkdirs();

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


    /**
     * Salva um objeto qualquer na url enviada
     * @param objectHM O objeto a ser salvo
     * @param  fileUrl A url onde ele deve ser armazenado
     */
    protected static void saveAnyObject(Object objectHM, String fileUrl) {
        new File (BASE_FOLDER).mkdirs();
        new File (BASE_FOLDER + "/ids/storage").mkdirs();

        ObjectOutputStream objectOut;
        FileOutputStream fileOut;

        try {
            System.out.println(fileUrl);
            fileOut = new FileOutputStream(fileUrl);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(objectHM);
            objectOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Acessa a url onde está salvo o objeto que armazena os Ids,
     * vê o valor salvo nesse objeto, adiciona 1, salva o objeto novamente
     * e retorna o Id no tipo String
     * @param  idUrl A url do objeto com o último Id
     * @return Retorna o Id num tipo String
     */
    protected static String generateId(String idUrl){
        new File (BASE_FOLDER).mkdirs();
        new File (BASE_FOLDER + "/ids/storage").mkdirs();

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
