package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Book;
import com.pbl.biblioteca.model.Loan;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.*;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */
public class LoanFileImpl extends ConnectionFile implements LoanDAO{

    /**
     * Salva um objeto Loan em um arquivo
     * @param  loanObject Loan que será salvo
     */
    @Override
    public void create(Loan loanObject){
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        loanHM.put(loanObject.getId(),loanObject);

        saveAnyObject(loanHM, loanFileUrl);

    }

    /**
     * Pega um objeto Loan salvo, através da primary key
     * @param  id O id é a primary key
     * @return Retorna o objeto Loan
     */
    @Override
    public Loan getByPK(String id){
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        return loanHM.get(id);
    }

    /**
     * Atualiza um objeto Loan em um arquivo
     * @param  loanObj Loan que será atualizado
     */
    @Override
    public void update(Loan loanObj) {
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        loanHM.put(loanObj.getId(), loanObj);

        saveAnyObject(loanHM, loanFileUrl);

    }

    /**
     * Deleta um objeto Loan de um arquivo
     * @param  id Primary key do Loan
     */
    @Override
    public void deleteByPK(String id) {
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);
        loanHM.remove(id);

        saveAnyObject(loanHM, loanFileUrl);
    }

    /**
     * Pega todos os Loans salvos atualmente
     * @return Retorna um Hashmap com todos as Loans, a key é o id
     */
    @Override
    public HashMap<String, Loan> getAll(){
        return getAnySavedHashmap(loanFileUrl);
    }

    /**
     * Gera um novo id
     * @return Retorna o id em uma String
     */
    @Override
    public String generateId() {
        return generateId(loanFileUrl);
    }

    /**
     * Pega todos os livros que estão emprestados atualmente, em ordem de popularidade
     * @return Retorna um Array de pairs, a key é o isbn do livro,
     *         o value é a quantidade de cópias desse livro sendo emprestadas atualmente
     */
    @Override
    public ArrayList<Pair<String, Integer>> getPopularBooksToday() {
        HashMap<String, Integer> totalLoansHM = new HashMap<>();
        HashMap<String, Loan> loanHM = getAnySavedHashmap(loanFileUrl);

        String newKey;

        for (String key : loanHM.keySet()){
            newKey = loanHM.get(key).getBookIsbn();

            if (totalLoansHM.get(newKey) == null){
                totalLoansHM.put(newKey, 1);
            } else {
                totalLoansHM.put(newKey, totalLoansHM.get(newKey) + 1);
            }
        }

        ArrayList<Pair<String, Integer>> popularOrdered = new ArrayList<>();

        for (String key : totalLoansHM.keySet()){
            popularOrdered.add(new Pair<>(key, totalLoansHM.get(key)));
        }

        popularOrdered.sort(Comparator.comparingInt(Pair<String, Integer>::getValue).reversed());

        return popularOrdered;
    }

    /**
     * Pega o total de empréstimos atuais
     * @return Retorna um inteiro com a quantidade de Laans
     */
    @Override
    public Integer getTotalLoans(){
        return getAnySavedHashmap(loanFileUrl).size();
    }

    /**
     * Pega um inteiro com a quantidade de empréstimos atrasados
     * @param  now Para fins de teste, é possível enviar uma data arbitrária,
     *             caso now = null, a data atual será utilizada
     * @return Retorna o inteiro correspondente a quantidade de Loans atrasados
     */
    @Override
    public Integer getTotalOverdueLoans(LocalDate now){
        if (now == null){
            now = LocalDate.now();
        }
        HashMap<String, Loan> allLoans = getAnySavedHashmap(loanFileUrl);
        Integer total = 0;

        for (String key : allLoans.keySet()){
            if (allLoans.get(key).getFinalDate().isBefore(now)){
                total ++;
            }
        }

        return total;
    }

    /**
     * Pega todos os empréstimos de um Reader
     * @param  username Primary key do Reader
     * @return Retorna um Array com todos os Loans
     */
    @Override
    public ArrayList<Loan> getAllFromUser(String username){
        HashMap<String, Loan> allLoans = getAnySavedHashmap(loanFileUrl);
        ArrayList<Loan> fromUser = new ArrayList<>();

        for (String key : allLoans.keySet()){
            if (allLoans.get(key).getUsername().equals(username)){
                fromUser.add(allLoans.get(key));
            }
        }

        return fromUser;
    }

    /**
     * Pega todos os empréstimos de um livro
     * @param  isbn Primary key do livro
     * @return Retorna um Array com todos os Loans
     */
    @Override
    public ArrayList<Loan> getAllFromBook(String isbn){
        HashMap<String, Loan> allLoans = getAnySavedHashmap(loanFileUrl);
        ArrayList<Loan> fromBook = new ArrayList<>();

        for (String key : allLoans.keySet()){
            if (allLoans.get(key).getBookIsbn().equals(isbn)){
                fromBook.add(allLoans.get(key));
            }
        }

        return fromBook;
    }
}
