package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class LocalSystem implements Serializable {

    public static User getNowUser() {
        return nowUser;
    }

    public static void setNowUser(User nowUser) {
        LocalSystem.nowUser = nowUser;
    }

    public static User nowUser;

    /**
     * Atualiza as reservas, deletando as que possuem usuários bloqueados, ou que já bateram no prazo
     * Seta a data final das reservas que já possuem cópias livres para empréstimo
     */
    public static void updateReserves(){
        HashMap<String, ArrayList<BookReserve>> allReserves = DAO.getBookReserveDAO().getAllByBook();
        Book nowBook;
        BookReserve nowReserve;
        ArrayList<BookReserve> nowArray;
        LocalDate nowEndReserveDate;
        Reader nowReader;
        int freeCopies; // copias liberadas, para que seja setada a data fim da reserva caso
                        // exista uma copia livre para o emprestimo

        for(String isbn : allReserves.keySet()){
            nowArray = allReserves.get(isbn);
            nowBook = DAO.getBookDAO().getByPK(isbn);
            freeCopies = nowBook.getAvailableCopies();

            for (int i = 0; i < nowArray.size(); i++){
                nowReserve = nowArray.get(i);
                nowEndReserveDate = nowReserve.getDateEndReserve();
                nowReader = DAO.getReaderDAO().getByPK(nowReserve.getUsername());

                if (i < freeCopies && nowEndReserveDate == null) {
                    nowReserve.setDateEndReserve(LocalDate.now().plusDays(2));
                    DAO.getBookReserveDAO().update(nowReserve);
                }

                if (nowReader.getBlocked()){
                    DAO.getBookReserveDAO().deleteByPK(nowReserve.getId());
                }

                if (nowEndReserveDate != null && nowEndReserveDate.isBefore(LocalDate.now())) {
                    DAO.getBookReserveDAO().deleteByPK(nowReserve.getId());
                }
            }
        }
    }

    /**
     * Atualiza os usuários, liberando o bloqueio daqueles que já cumpriram o prazo
     */
    public static void updateReadersBlockStatus(){
        HashMap<String, Reader> all = DAO.getReaderDAO().getAll();
        Reader nowReader;

        for (String id : all.keySet()){
            nowReader = all.get(id);

            if (nowReader.getBlocked().equals(true)
                    && nowReader.getDateEndBlock().isBefore(LocalDate.now())){

                nowReader.setBlocked(false);
                DAO.getReaderDAO().update(nowReader);
            }
        }
    }

    /**
     * Atualiza os usuários e as reservas
     */
    public static void updateSystem(){
        updateReadersBlockStatus();
        updateReserves();
    }

    /**
     * Loga um usuário
     * @param  username Username do usuário
     * @param  password Senha do usuário
     * @param  type Tipo do usuário (librarian, admin ou reader)
     * @return Retorna o objeto User
     * @throws wrongPasswordException Caso a senha seja inválida
     * @throws notFoundException Caso não exista um usuário de mesmo username e tipo
     */
    public static User login(String username, String password, String type) throws
            wrongPasswordException, notFoundException{

        return DAO.getUserDAO().login(username, password, type);
    }



}
