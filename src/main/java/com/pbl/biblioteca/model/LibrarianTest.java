package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.LibrarianDAO;
import com.pbl.biblioteca.dao.LoanDAO;

import java.util.ArrayList;

public class LibrarianTest {

    public static void main(String[] args){
        Librarian librarianTest = new Librarian("biblio_mendes", "12345");
        LibrarianDAO.saveLibrarian(librarianTest);
        User user0 = new User("pedrin658", "Pedro M.", "Pampalona",
                "7598191919", "12345");
        Loan loanTest = librarianTest.setBookLoan("54321", user0, 7);
        librarianTest.setBookLoan("521", user0, 7);
        LibrarianDAO.saveLibrarian(librarianTest);
        String id = loanTest.getId();

        Loan savedLoan = LoanDAO.getLoanById(id);
        System.out.println("ISBN: " + savedLoan.getbookISBN());
        System.out.println("Initial Date: " + savedLoan.getInitialDate());
        System.out.println("Final Date: " + savedLoan.getFinalDate());
        System.out.println("Nickname: " + savedLoan.getUserNickname());
        System.out.println("Id: " + savedLoan.getId());

        Librarian savedLibrarian = LibrarianDAO.getLibrarianByUsername("biblio_mendes");
        System.out.println("Librarian: ");
        System.out.println(savedLibrarian.getId());
        System.out.println(savedLibrarian.getUsername());
        System.out.println(savedLibrarian.getPassword());
        ArrayList<String> loanIds = savedLibrarian.getLoanIds();
        System.out.println(loanIds.get(1));
    }
}
