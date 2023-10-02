package com.pbl.biblioteca.dao.Book;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class BookFileImpl extends ConnectionFile implements BookDAO{

    /**
     * Salva um objeto Book em um arquivo
     * @param  bookObject Book que será salvo
     */
    @Override
    public void create(Book bookObject) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        bookHM.put(bookObject.getIsbn(), bookObject);

        saveAnyObject(bookHM, bookFileUrl);
    }

    /**
     * Pega um objeto Book salvo, através da primary key
     * @param  isbn O isbn é a primary key dos Users
     * @return Retorna o objeto Book
     */
    @Override
    public Book getByPK(String isbn) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);

        return bookHM.get(isbn);
    }

    /**
     * Atualiza um objeto Book em um arquivo
     * @param  bookObj Book que será atualizado
     */
    @Override
    public void update(Book bookObj) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        String isbn = bookObj.getIsbn();
        bookHM.put(isbn, bookObj);
        saveAnyObject(bookHM, bookFileUrl);

    }

    /**
     * Deleta um objeto Book em um arquivo
     * @param  isbn Primary key do livro que será deletado
     */
    @Override
    public void deleteByPK(String isbn) {
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        if (bookHM.containsKey(isbn)) {
            bookHM.remove(isbn);
            saveAnyObject(bookHM, bookFileUrl);
        }
    }

    /**
     * Pega todos os livros salvos atualmente
     * @return Retorna um Hashmap com todos os livros, a key é o isbn
     */
    @Override
    public HashMap<String, Book> getAll() {
        return getAnySavedHashmap(bookFileUrl);
    }

    /**
     * Pega todos os livros de uma determinada categoria
     * @param  category A categoria dos livros
     * @return Retorna um array com todos os livros
     */
    @Override
    public ArrayList<Book> getAllBooksFromCategory(String category) {

        ArrayList<Book> booksFromCategory = new ArrayList<>();
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        Book nowBook = null;

        for (String isbn : bookHM.keySet()) {
            nowBook = bookHM.get(isbn);
            if (nowBook.getCategory().equals(category)) {
                booksFromCategory.add(nowBook);
            }
        }

        return booksFromCategory;
    }

    /**
     * Gera um novo id para o livro
     * @return Retorna o id em uma String
     */
    @Override
    public String generateId() {
        return generateId(bookFileUrl);
    }

    /**
     * Busca por livros que possuam um título parecido com o param
     * @param  title Parte do título do livro
     * @return Retorna um array com todos os matches
     */
    @Override
    public ArrayList<Book> searchByTitle(String title){
        ArrayList<Book> matches = new ArrayList<>();
        HashMap<String, Book> bookHM = getAnySavedHashmap(bookFileUrl);
        Book nowBook;
        String nowTitle;
        title = title.toLowerCase();

        for (String key : bookHM.keySet()){
            nowBook = bookHM.get(key);
            nowTitle = nowBook.getTitle().toLowerCase();

            if(nowTitle.equals(title)){ // Acerto exato sempre ficará em primeiro
                if (matches.isEmpty()){
                    matches.add(nowBook);
                } else {
                    matches.add(matches.get(0));
                    matches.set(0, nowBook);
                }
            } else if (nowTitle.matches(".*" + title + ".*")){
                matches.add(nowBook);
            }
        }

        return matches;
    }
}

