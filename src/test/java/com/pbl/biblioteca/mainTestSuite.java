package com.pbl.biblioteca;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * @author      Pedro Mendes <mendes @ ecomp.uefs.br>
 * @version     1.0
 */

@Suite
@SelectPackages({"com.pbl.biblioteca.dao", "com.pbl.biblioteca.model"})
public class mainTestSuite {
}
