package com.pbl.biblioteca;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectPackages({"com.pbl.biblioteca.dao", "com.pbl.biblioteca.model"})

public class mainTestSuite {
}
