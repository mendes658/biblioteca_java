package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @BeforeEach
    void setUp() {
        ConnectionFile.setTestFileUrls();
        ConnectionMemory.clearMemory();
        ConnectionFile.clearTestFiles();
    }

    @AfterEach
    void tearDown() {
        ConnectionFile.setDefaultFileUrls();
        ConnectionFile.clearTestFiles();
        ConnectionMemory.clearMemory();
    }

}