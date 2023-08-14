package com.pbl.biblioteca.dao.Loan;

import com.pbl.biblioteca.dao.crudDAO;

public interface LoanDAO<Loan> extends crudDAO<Loan>{
    String generateId();
}
