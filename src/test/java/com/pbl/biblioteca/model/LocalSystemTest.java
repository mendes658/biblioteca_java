package com.pbl.biblioteca.model;

import com.pbl.biblioteca.dao.ConnectionFile;
import com.pbl.biblioteca.dao.ConnectionMemory;
import com.pbl.biblioteca.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocalSystemTest {
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

    @Test
    void updateSystem(){
        Reader r1 = new Reader("pedro", "12345",
                "rua", "75", "pedrom");
        Book b1 = new Book("Teco teleco teco", "Amarelo", "Vermelho",
                2002, "Mistério", "11111", 0);
        DAO.getReaderDAO().create(r1);
        DAO.getBookDAO().create(b1);

        BookReserve br = new BookReserve(r1.getUsername(), b1.getIsbn());
        DAO.getBookReserveDAO().create(br);

        assertEquals(1, DAO.getBookReserveDAO().getAll().size());

        r1.setBlocked(true);
        DAO.getReaderDAO().update(r1);

        // Testando remoção das reservas de usuários bloqueados
        LocalSystem.updateReserves();
        assertEquals(0, DAO.getBookReserveDAO().getAll().size());

        r1.setBlocked(false);
        DAO.getReaderDAO().update(r1);
        DAO.getBookReserveDAO().create(br);

        LocalSystem.updateReserves();
        assertNull(DAO.getBookReserveDAO().getByPK(br.getId()).getDateEndReserve());

        b1.addCopies(1);
        DAO.getBookDAO().update(b1);

        LocalSystem.updateReserves();

        // Testando o set da data final da reserva após uma cópia ser liberada
        // Tem que ser setada para dois dias após o dia da liberação
        assertEquals(LocalDate.now().plusDays(2),
                DAO.getBookReserveDAO().getByPK(br.getId()).getDateEndReserve());

        r1.setBlocked(true);
        r1.setDateEndBlock(LocalDate.now().minusDays(1));
        DAO.getReaderDAO().update(r1);
        assertTrue(r1.getBlocked());

        LocalSystem.updateSystem();

        r1 = DAO.getReaderDAO().getByPK(r1.getUsername());

        // Testando a liberação de um usuário após a data de fim de bloqueio ter passado
        assertFalse(r1.getBlocked());

    }
}