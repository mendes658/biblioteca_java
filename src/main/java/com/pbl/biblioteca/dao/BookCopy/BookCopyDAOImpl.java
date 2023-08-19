package com.pbl.biblioteca.dao.BookCopy;

import com.pbl.biblioteca.dao.ConnectionDAO;
import com.pbl.biblioteca.model.BookCopy;

import java.util.HashMap;

public class BookCopyDAOImpl extends ConnectionDAO implements BookCopyDAO<BookCopy> {

    @Override
    public boolean create(BookCopy bookCopyObj) {
        HashMap<String, BookCopy> bookCopyHM = getAnySavedHashmap(bookCopiesUrl);
        bookCopyHM.put(bookCopyObj.getId(), bookCopyObj);

        return saveAnyObject(bookCopyHM, bookCopiesUrl);
    }

    @Override
    public BookCopy getByPK(String id) {
        HashMap<String, BookCopy> bookCopyHM = getAnySavedHashmap(bookCopiesUrl);

        return bookCopyHM.get(id);
    }

    @Override
    public boolean update(BookCopy bookCopyObj) {
        HashMap<String, BookCopy> bookCopyHM = getAnySavedHashmap(bookCopiesUrl);
        bookCopyHM.put(bookCopyObj.getId(), bookCopyObj);

        return saveAnyObject(bookCopyHM, bookCopiesUrl);
    }

    @Override
    public boolean deleteByPK(String id) {
        HashMap<String, BookCopy> bookCopyHM = getAnySavedHashmap(bookCopiesUrl);
        bookCopyHM.remove(id);

        return saveAnyObject(bookCopyHM, bookCopiesUrl);
    }

    @Override
    public HashMap<String, BookCopy> getAll() {
        return getAnySavedHashmap(bookCopiesUrl);
    }

    @Override
    public String generateId() {
        return ConnectionDAO.generateId(bookCopiesUrl);
    }
}
