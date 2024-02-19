package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.isbnAlreadyInUseException;
import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.usernameAlreadyInUseException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class Admin extends Operator implements Serializable {

    public Admin(String username, String password, String address, String telephone,
                     String name){
        super(username, password, address, telephone, name, "admin");

    }

    /**
     * Cria e salva um usuário
     * @param  username Username do usuário
     * @param  password Senha do usuário
     * @param  address Endereço do usuário
     * @param  telephone Telefone do usuário
     * @param  name Nome do usuário
     * @param  type Tipo do usuário (reader, admin ou librarian)
     * @return Retorna o objeto User de acordo
     * @throws usernameAlreadyInUseException Caso já exista um usuário de mesmo tipo e username
     */
    public User createUser(String username, String password, String address, String telephone,
                           String name, String type) throws usernameAlreadyInUseException {

        switch (type) {
            case "reader" -> {
                Reader reader = DAO.getReaderDAO().getByPK(username);

                if (reader != null){
                    throw new usernameAlreadyInUseException("Username in use");
                }

                reader = new Reader(username, name, address, telephone, password);
                DAO.getReaderDAO().create(reader);
                return reader;
            }
            case "librarian" -> {
                Librarian librarian = DAO.getLibrarianDAO().getByPK(username);

                if (librarian != null){
                    throw new usernameAlreadyInUseException("Username in use");
                }

                librarian = new Librarian(username, password, address, telephone, name);
                DAO.getLibrarianDAO().create(librarian);
                return librarian;
            }
            case "admin" -> {
                Admin admin = DAO.getAdminDAO().getByPK(username);

                if (admin != null){
                    throw new usernameAlreadyInUseException("Username in use");
                }

                admin = new Admin(username, password, address, telephone, name);
                DAO.getAdminDAO().create(admin);
                return admin;
            }
        }

        return null;
    }

    /**
     * Deleta um usuário, bem como suas reservas
     * @param user User a ser deletado
     */
    public void deleteUser(User user){
       String type = user.getType();

       switch (type){
           case "reader" -> {
               DAO.getReaderDAO().deleteByPK(user.getUsername());
               ArrayList<BookReserve> reservesUser = DAO.getBookReserveDAO().getAllFromReader(user.getUsername());

               for(BookReserve reserve : reservesUser){
                   DAO.getBookReserveDAO().deleteByPK(reserve.getId());
               }
           }
           case "admin" -> DAO.getAdminDAO().deleteByPK(user.getUsername());
           case "librarian" -> DAO.getLibrarianDAO().deleteByPK(user.getUsername());
       }
    }

    /**
     * Atualiza um usuário
     * @param user User a ser atualizado
     */
    public void updateUser(User user){
        String type = user.getType();

        switch (type){
            case "reader" -> DAO.getReaderDAO().update((Reader) user);
            case "admin" -> DAO.getAdminDAO().update((Admin) user);
            case "librarian" -> DAO.getLibrarianDAO().update((Librarian) user);
        }
    }

    /**
     * Pega um objeto User salvo, através da primary key e do tipo
     * @param  username O username é a primary key
     * @param  type Tipo (reader, librarian ou admin)
     * @return Retorna o objeto User
     * @throws notFoundException Caso o tipo informado seja inválido
     */
    public User getUser(String username, String type) throws notFoundException {
        switch (type){
            case "reader" -> {
                User u = DAO.getReaderDAO().getByPK(username);
                if (u != null){return u;}
            }
            case "admin" -> {
                User u = DAO.getAdminDAO().getByPK(username);
                if (u != null){return u;}
            }
            case "librarian" -> {
                User u = DAO.getLibrarianDAO().getByPK(username);
                if (u != null){return u;}
            }
        }

        throw new notFoundException("Wrong type");
    }

    /**
     * Bloqueia um Reader
     * @param  reader Reader a ser bloqueado
     * @param days Dias que ele ficará bloqueado, se days = -1,
     *             o bloqueio tem o fim indeterminado
     */
    public void blockReader(Reader reader, Integer days){
        if (days == -1){
            reader.setDateEndBlock(null);
        } else {
            reader.setDateEndBlock(LocalDate.now().plusDays(days));
        }

        reader.setBlocked(true);
        DAO.getReaderDAO().update(reader);
    }

    /**
     * Desbloqueia um Reader
     * @param  reader Reader a ser desbloqueado
     */
    public void unblockReader(Reader reader){
        reader.setBlocked(false);
        reader.setDateEndBlock(null);
        DAO.getReaderDAO().update(reader);
    }

    /**
     * Adiciona novas cópias à um livro
     * @param  book Livro em questão
     * @param total Total de cópias adicionadas
     */
    public void addBookCopies(Book book, Integer total){
        book.addCopies(total);

        DAO.getBookDAO().update(book);
    }

    /**
     * Remove cópias de um livro
     * @param  book Livro em questão
     * @param total Total de cópias removidas
     * @throws notFoundException Caso a quantidade de cópias disponíveis seja menor que o total
     */
    public void removeBookCopies(Book book, Integer total) throws notFoundException{
        book.removeCopies(total);

        DAO.getBookDAO().update(book);
    }


    /**
     * Cria e salva um livro
     * @param  title Título
     * @param  author Autor
     * @param  publisher Editora
     * @param  year Ano
     * @param  category Categoria
     * @param  isbn Isbn (Primary Key)
     * @param  totalCopies Total de cópias do livro
     * @return Retorna o objeto User de acordo
     * @throws isbnAlreadyInUseException Caso já exista um livro com o isbn informado
     */
    public Book createBook(String title, String author, String publisher,
                           Integer year, String category, String isbn, Integer totalCopies)
    throws isbnAlreadyInUseException {

        if (DAO.getBookDAO().getByPK(isbn) != null){
            throw new isbnAlreadyInUseException("The given isbn is already in use");
        }

        Book b1 = new Book(title, author, publisher, year, category, isbn, totalCopies);
        DAO.getBookDAO().create(b1);

        return b1;
    }

    /**
     * Deleta um livro, bem como suas reservas e empréstimos
     * @param book Livro a ser deletado
     */
    public void deleteBook(Book book){
        ArrayList<BookReserve> reservesBook = DAO.getBookReserveDAO().getAllFromBook(book.getIsbn());
        ArrayList<Loan> loansBook = DAO.getLoanDAO().getAllFromBook(book.getIsbn());

        for(BookReserve reserve : reservesBook){
            DAO.getBookReserveDAO().deleteByPK(reserve.getId());
        }

        for(Loan lo : loansBook){
            DAO.getLoanDAO().deleteByPK(lo.getId());
        }

        DAO.getBookDAO().deleteByPK(book.getIsbn());
    }

    public void updateBook(Book book){
        DAO.getBookDAO().update(book);
    }
}
