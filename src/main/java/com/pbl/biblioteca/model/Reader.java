package com.pbl.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class Reader extends User implements Serializable {

    private Boolean blocked;
    private LocalDate dateEndBlock;

    public Reader(String username, String name,
                  String address, String telephone, String password){

        super(username, password, address, telephone, name, "reader");
        this.blocked = false;
        this.dateEndBlock = null;

    }


    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public void setDateEndBlock(LocalDate dateEndBlock) {
        this.dateEndBlock = dateEndBlock;
    }

    public Boolean getBlocked(){
        return blocked;
    }

    public LocalDate getDateEndBlock(){
        return dateEndBlock;
    }

    /**
     * Cria uma nova reserva
     * @param  book Livro que será reservado
     * @throws readerIsBlockedException Caso exista algum status do usuário impedindo-o de efetuar
     * a reserva, por exemplo, empréstimos em atraso ou status de bloqueado
     * @throws fullException Caso já existam muitas reservas para o livro, ou do usuário em geral
     * @throws copyAvailableException Caso exista uma cópia disponível para empréstimo
     */
    public void createBookReserve(Book book) throws readerIsBlockedException, fullException,
            copyAvailableException{
        if (this.blocked){
            throw new readerIsBlockedException("Reader is blocked");
        }

        ArrayList<BookReserve> reserves = DAO.getBookReserveDAO().getReservesFromBook(book.getIsbn());
        ArrayList<Loan> loansFromReader = DAO.getLoanDAO().getAllFromUser(this.getUsername());

        for (Loan l : loansFromReader){
            if (l.getFinalDate().isBefore(LocalDate.now())){
                throw new readerIsBlockedException("Reader has an active overdue loan");
            }
            if (l.getBookIsbn().equals(book.getIsbn())){
                throw new readerIsBlockedException("Reader is already borrowing this book");
            }
        }

        for (BookReserve reserve : reserves){
            if (reserve.getUsername().equals(this.getUsername())){
                throw new readerIsBlockedException("Reader already reserved this book");
            }
        }

        if (reserves.size() > 2){
            throw new fullException("Too many reserves to current book");
        }

        if (book.getAvailableCopies() > 0){
            throw new copyAvailableException("There are copies available to borrow, can't reserve");
        }

        if (DAO.getBookReserveDAO().getAllFromReader(this.getUsername()).size() > 2){
            throw new fullException("Too many active reserves from current user");
        }

        BookReserve reserve = new BookReserve(this.getUsername(), book.getIsbn());

        DAO.getBookReserveDAO().create(reserve);
        DAO.getReportDAO().logNewReserve(reserve);

    }

    /**
     * Deleta a reserva do usuário para um livro
     * @param  book Livro que terá sua reserva deletada
     * @throws notFoundException Caso não exista uma reserva do usuário para o livro indicado
     */
    public void removeReserve(Book book) throws notFoundException{
        ArrayList<BookReserve> reserves = DAO.getBookReserveDAO().getAllFromReader(this.getUsername());

        for (BookReserve r : reserves){
            if (r.getBookIsbn().equals(book.getIsbn())){
                DAO.getBookReserveDAO().deleteByPK(r.getId());
                return;
            }
        }

        throw new notFoundException("No reserve to current book was found");

    }

    /**
     * Busca por livros que possuam um título parecido com o param
     * @param  title O título do livro
     * @return Retorna um array com todos os matches
     */
    public ArrayList<Book> searchBookByTitle(String title){
        return DAO.getBookDAO().searchByTitle(title);
    }

    /**
     * Busca por livros que possuam um isbn parecido com o param
     * @param  isbn O isbn do livro
     * @return Retorna um array com todos os matches
     */
    public ArrayList<Book> searchBookByIsbn(String isbn){
        return DAO.getBookDAO().searchByIsbn(isbn);
    }

    /**
     * Busca por livros que possuam um autor parecido com o param
     * @param  author O autor do livro
     * @return Retorna um array com todos os matches
     */
    public ArrayList<Book> searchBookByAuthor(String author){
        return DAO.getBookDAO().searchByAuthor(author);
    }

    /**
     * Busca por livros que possuam uma categoria parecido com o param
     * @param  category A categoria do livro
     * @return Retorna um array com todos os matches
     */
    public ArrayList<Book> searchBookByCategory(String category){
        return DAO.bookDAO.searchByCategory(category);
    }

    /**
     * Renova o empréstimo para mais 7 dias a partir do dia atual
     * @throws alreadyRenewedException Caso este empréstimo já tenha sido renovado
     * @throws tooManyReservesException Caso existam reservas para o livro
     */
    public void renewLoan(Loan loan) throws alreadyRenewedException, tooManyReservesException{
        loan.renew();
    }
}
