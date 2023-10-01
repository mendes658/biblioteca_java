package com.pbl.biblioteca.dao.BookReserve;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.BookReserve;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class BookReserveFileImpl extends ConnectionFile implements BookReserveDAO{

    /**
     * Salva um objeto BookReserve em um arquivo
     * @param  bookReserveObj BookReserve que será salvo
     */
    @Override
    public void create(BookReserve bookReserveObj) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);

        bookReserveHM.put(bookReserveObj.getId(),bookReserveObj);

        saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    /**
     * Deleta um objeto BookReserve em um arquivo
     * @param  id Primary key da reserva que será deletada
     */
    @Override
    public void deleteByPK(String id) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        bookReserveHM.remove(id);

        saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    /**
     * Atualiza um objeto BookReserve em um arquivo
     * @param  bookReserveObj BookReserve que será atualizado
     */
    @Override
    public void update(BookReserve bookReserveObj) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        bookReserveHM.put(bookReserveObj.getId(), bookReserveObj);

        saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    /**
     * Pega um objeto BookReserve salvo, através da primary key
     * @param  id O id é a primary key
     * @return Retorna o objeto BookReserve
     */
    @Override
    public BookReserve getByPK(String id) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);

        return bookReserveHM.get(id);
    }

    /**
     * Pega todas as reservas salvas atualmente
     * @return Retorna um Hashmap com todos as reservas, a key é o id
     */
    @Override
    public HashMap<String, BookReserve> getAll() {
        return  getAnySavedHashmap(bookReserveUrl);
    }

    /**
     * Gera um novo id para a reserva
     * @return Retorna o id numa String
     */
    @Override
    public String generateId() {
        return generateId(bookReserveUrl);
    }

    /**
     * Busca por todas as reservas de um livro
     * @param  bookIsbn O isbn do livro
     * @return Retorna um array com todas as reservas
     */
    @Override
    public ArrayList<BookReserve> getReservesFromBook(String bookIsbn) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<BookReserve> allFromBook = new ArrayList<>();

        for (String key : bookReserveHM.keySet()){
            if(bookReserveHM.get(key).getBookIsbn().equals(bookIsbn)){
                allFromBook.add(bookReserveHM.get(key));
            }
        }

        allFromBook.sort(Comparator.comparingInt(BookReserve::epochDateSetReserve));

        return allFromBook;
    }

    /**
     * Deleta todas as reservas de um Reader
     * @param  username A primary key do Reader
     */
    @Override
    public void removeAllFromReader(String username) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<String> toRemove = new ArrayList<>();

        for (String key : bookReserveHM.keySet()){
            if (bookReserveHM.get(key).getUsername().equals(username)){
                toRemove.add(key);
            }
        }

        for (String key : toRemove){
            bookReserveHM.remove(key);
        }

        saveAnyObject(bookReserveHM, bookReserveUrl);
    }

    /**
     * Busca por todas as reservas de um Reader
     * @param  username A primary key do Reader
     * @return Retorna um array com todas as reservas
     */
    @Override
    public ArrayList<BookReserve> getAllFromReader(String username) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<BookReserve> allFromUser = new ArrayList<>();

        for (String key : bookReserveHM.keySet()){
            if (bookReserveHM.get(key).getUsername().equals(username)){
                allFromUser.add(bookReserveHM.get(key));
            }
        }

        return allFromUser;
    }

    /**
     * Pega todas as reservas, organizadas livro a livro
     * @return Retorna um HashMap, a chave é o isbn do livro,
     * o valor é um Array com todas as reservas daquele livro
     */
    @Override
    public HashMap<String, ArrayList<BookReserve>> getAllByBook(){
        HashMap<String, ArrayList<BookReserve>> allByBook = new HashMap<>();
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        BookReserve nowReserve;
        ArrayList<BookReserve> nowArray;

        for (String key : bookReserveHM.keySet()){
            nowReserve = bookReserveHM.get(key);
            nowArray = allByBook.getOrDefault(nowReserve.getBookIsbn(), new ArrayList<>());

            nowArray.add(nowReserve);

            allByBook.put(nowReserve.getBookIsbn(), nowArray);
        }

        for (String key : allByBook.keySet()){
            nowArray = allByBook.get(key);
            nowArray.sort(Comparator.comparingInt(BookReserve::epochDateSetReserve));
            allByBook.put(key, nowArray);
        }

        return allByBook;
    }

    /**
     * Pega o total de reservas ativas
     * @return Retorna um Inteiro com o total de reservas
     */
    @Override
    public Integer getTotalReserves(){
        return getAll().size();
    }


    /**
     * Busca por todas as reservas de um livro
     * @param  isbn A primary key do livro
     * @return Retorna um array com todas as reservas
     */
    @Override
    public ArrayList<BookReserve> getAllFromBook(String isbn) {
        HashMap<String, BookReserve> bookReserveHM = getAnySavedHashmap(bookReserveUrl);
        ArrayList<BookReserve> allFromBook = new ArrayList<>();

        for (String key : bookReserveHM.keySet()){
            if (bookReserveHM.get(key).getBookIsbn().equals(isbn)){
                allFromBook.add(bookReserveHM.get(key));
            }
        }

        return allFromBook;
    }
}
