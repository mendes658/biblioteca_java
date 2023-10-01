package com.pbl.biblioteca.dao.Admin;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.model.Admin;

import java.util.HashMap;

public class AdminFileImpl extends ConnectionFile implements AdminDAO{

    /**
     * Salva um objeto Admin em um arquivo
     * @param  adminObject Admin que será salvo
     */
    @Override
    public void create(Admin adminObject){
        HashMap<String, Admin> adminHm = getAnySavedHashmap(adminUrl);

        adminHm.put(adminObject.getUsername(),adminObject);

        saveAnyObject(adminHm, adminUrl);
    }

    /**
     * Pega um objeto Admin salvo, através da primary key
     * @param  username O username é a primary key dos Users
     * @return Retorna o objeto Admin
     */
    @Override
    public Admin getByPK(String username) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap(adminUrl);
        return adminHm.get(username);
    }

    /**
     * Pega todos os Admins salvos atualmente
     * @return Retorna um Hashmap com todos os admins, a key é o username
     */
    @Override
    public HashMap<String, Admin> getAll() {
        return getAnySavedHashmap(adminUrl);
    }

    /**
     * Atualiza um objeto Admin em um arquivo
     * @param  adminObject Admin que será atualizado
     */
    @Override
    public void update(Admin adminObject) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap(adminUrl);
        adminHm.put(adminObject.getUsername(), adminObject);

        saveAnyObject(adminHm, adminUrl);
    }

    /**
     * Deleta um objeto Admin em um arquivo
     * @param  username Primary key do Admin que será deletado
     */
    @Override
    public void deleteByPK(String username) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap(adminUrl);
        adminHm.remove(username);

        saveAnyObject(adminHm, adminUrl);
    }
}
