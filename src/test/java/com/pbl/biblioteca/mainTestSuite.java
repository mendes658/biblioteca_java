package com.pbl.biblioteca;


import com.pbl.biblioteca.dao.BookCopy.BookCopyDAOImpl;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.jupiter.api.*;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectPackages({"com.pbl.biblioteca.dao", "com.pbl.biblioteca.model"})

public class mainTestSuite {
}
