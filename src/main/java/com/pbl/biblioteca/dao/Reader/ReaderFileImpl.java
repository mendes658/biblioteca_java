package com.pbl.biblioteca.dao.Reader;

import com.pbl.biblioteca.model.Reader;

import java.util.HashMap;

public class ReaderFileImpl implements ReaderDAO{
    @Override
    public boolean update(Reader obj) {
        return false;
    }

    @Override
    public boolean deleteByPK(String pk) {
        return false;
    }

    @Override
    public boolean create(Reader obj) {
        return false;
    }

    @Override
    public Reader getByPK(String pk) {
        return null;
    }

    @Override
    public HashMap<String, Reader> getAll() {
        return null;
    }
}
