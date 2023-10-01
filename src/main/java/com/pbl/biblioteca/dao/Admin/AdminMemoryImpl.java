package com.pbl.biblioteca.dao.Admin;


import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.model.Admin;

import java.util.HashMap;

public class AdminMemoryImpl extends ConnectionMemory implements AdminDAO{

    /**
     * Salva um objeto Admin na memória
     * @param  adminObject Admin que será salvo
     */
    @Override
    public void create(Admin adminObject){
        HashMap<String, Admin> adminHm = getAnySavedHashmap("admin");

        adminHm.put(adminObject.getUsername(), adminObject);
        saveAnyObject(adminHm, "admin");
    }

    /**
     * Pega um objeto Admin salvo, através da primary key
     * @param  username O username é a primary key dos Users
     * @return Retorna o objeto Admin
     */
    @Override
    public Admin getByPK(String username) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap("admin");

        return adminHm.get(username);
    }

    /**
     * Pega todos os Admins salvos atualmente
     * @return Retorna um Hashmap com todos os admins, a key é o username
     */
    @Override
    public HashMap<String, Admin> getAll() {
        return getAnySavedHashmap("admin");
    }

    /**
     * Atualiza um objeto Admin na memória
     * @param  adminObject Admin que será atualizado
     */
    @Override
    public void update(Admin adminObject) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap("admin");
        adminHm.put(adminObject.getUsername(), adminObject);

        saveAnyObject(adminHm, "admin");
    }

    /**
     * Deleta um objeto Admin da memória
     * @param  username Primary key do Admin que será deletado
     */
    @Override
    public void deleteByPK(String username) {
        HashMap<String, Admin> adminHm = getAnySavedHashmap("admin");
        adminHm.remove(username);

        saveAnyObject(adminHm, "admin");
    }
}
