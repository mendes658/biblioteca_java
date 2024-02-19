package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class Librarian extends Operator{


    public Librarian(String username, String password, String address, String telephone,
                     String name){
        super(username, password, address, telephone, name, "librarian");

    }

    /**
     * Cria um novo empréstimo
     * @param  book Livro que será emprestado
     * @param  reader Reader que pegará o livro emprestado
     * @param  days Dias até o fim do empréstimo
     * @return Retorna o objeto Loan do empréstimo
     * @throws readerIsBlockedException Caso exista algum status do usuário impedindo-o de efetuar
     * o empréstimo, por exemplo, empréstimos em atraso ou status de bloqueado
     * @throws notFoundException Caso não existam cópias disponíveis
     * @throws fullException Caso o usuário já tenha este livro emprestado, ou ele tenha atingido
     * o limite de 3 empréstimos
     * @throws tooManyReservesException Caso existam reservas para o livro, e o reader não tenha uma
     * posição liberada na fila
     */
    public Loan createBookLoan(Book book, Reader reader, Integer days) throws
            readerIsBlockedException, notFoundException, fullException,
            tooManyReservesException{

        ArrayList<Loan> allFromUser = DAO.getLoanDAO().getAllFromUser(reader.getUsername());

        for (Loan loan : allFromUser){
            if (loan.getUsername().equals(reader.getUsername())){
                throw new fullException("Reader is already borrowing this book");
            }
            if (loan.getFinalDate().isBefore(LocalDate.now())){
                throw new readerIsBlockedException("Reader has an active overdue loan");
            }
        }

        if (reader.getBlocked()) {
            throw new readerIsBlockedException("Reader is blocked.");
        }

        if (allFromUser.size() > 2){
            throw new fullException("Reader has too many active loans");
        }


        if (book.getAvailableCopies() < 1){
            throw new notFoundException("There are no copies available");
        }

        ArrayList<BookReserve> reserves = DAO.getBookReserveDAO().getReservesFromBook(book.getIsbn());

        int reserveIndex = -1;
        int reservesSize = reserves.size();

        if (!reserves.isEmpty()){
            for (int i = 0; i < reservesSize; i++){
                if (reserves.get(i).getUsername().equals(reader.getUsername())){
                    reserveIndex = i;
                    break;
                }
            }
        }

        if (reserveIndex == -1){ // Se o usuário não possui uma reserva
            if (book.getAvailableCopies() <= reservesSize){ // se a quantidade de copias for <= q a de reservas
                throw new tooManyReservesException("There are too many reserves to current book");
            }
        } else {
            // Se a posição do usuario na fila for maior que a quantidade de cópias disponiveis
            if (reserveIndex >= book.getAvailableCopies()){
                throw new tooManyReservesException("There are too many reserves to current book");
            }
        }

        if (reserveIndex != -1){
            DAO.getBookReserveDAO().deleteByPK(reserves.get(reserveIndex).getId());
        }

        book.borrowCopy();
        Loan newLoan = new Loan(book.getIsbn(), reader.getUsername(), days, this.getUsername());

        DAO.getReportDAO().logNewLoan(newLoan);
        DAO.getLoanDAO().create(newLoan);
        DAO.getBookDAO().update(book);



        return newLoan;
    }

    /**
     * Deleta um empréstimo
     * @param loanId Id do empréstimo
     * @return Quantidade de dias que o usuário ficará multado
     */
    public int deleteBookLoan(String loanId){
        Loan toDelete = DAO.getLoanDAO().getByPK(loanId);
        Book book = DAO.getBookDAO().getByPK(toDelete.getBookIsbn());
        Reader reader = DAO.getReaderDAO().getByPK(toDelete.getUsername());
        int daysBlock = 0;

        book.retrieveCopy();

        if (toDelete.getFinalDate().isBefore(LocalDate.now())){
            daysBlock = (int) (LocalDate.now().toEpochDay() - toDelete.getFinalDate().toEpochDay());
            reader.setBlocked(true);
            reader.setDateEndBlock(LocalDate.now().plusDays(daysBlock));
        }

        DAO.getLoanDAO().deleteByPK(loanId);
        DAO.getBookDAO().update(book);
        DAO.getReaderDAO().update(reader);

        return daysBlock;
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
}
