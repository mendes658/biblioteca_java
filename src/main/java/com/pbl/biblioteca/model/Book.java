package com.pbl.biblioteca.model;



import com.pbl.biblioteca.dao.Book.BookFileImpl;
import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.notFoundException;

import java.io.Serializable;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class Book implements Serializable {

    private String title;
    private String author;
    private String publisher;
    private Integer year;
    private String category;
    private final String isbn; // unique
    private final String id;

    private Integer totalCopies;
    private Integer availableCopies;


    public Book(String title, String author, String publisher,
                Integer year, String category, String isbn, Integer totalCopies) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.category = category;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;

        BookFileImpl bookDAO = new BookFileImpl();
        this.id = bookDAO.generateId();

    }


    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public String getIsbn() {
        return isbn;
    }


    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Adiciona novas cópias ao livro
     * @param total Total de cópias adicionadas
     */
    public void addCopies(Integer total){
        totalCopies += total;
        availableCopies += total;

        DAO.getBookDAO().update(this);
    }

    /**
     * Remove cópias do livro
     * @param total Total de cópias removidas
     * @throws notFoundException Caso a quantidade de cópias disponíveis seja menor que o total
     */
    public void removeCopies(Integer total) throws notFoundException{
        if (total <= availableCopies){
            totalCopies -= total;
            availableCopies -= total;
        } else {
            throw new notFoundException("No copies available to remove");
        }

        DAO.getBookDAO().update(this);
    }

    /**
     * Empresta uma cópia do livro
     * @throws notFoundException Caso não possuam cópias disponíveis para empréstimo
     */
    public void borrowCopy() throws notFoundException {
        if (this.availableCopies < 1){
            throw new notFoundException("No copy found");
        } else {
            this.availableCopies --;
        }

        DAO.getBookDAO().update(this);
    }

    /**
     * Devolve uma cópia do livro
     */
    public void retrieveCopy() {
        availableCopies ++;

        DAO.getBookDAO().update(this);
    }


}


