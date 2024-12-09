package cabinet.repo;

import cabinet.domeniu.Pacient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryFileRepositoryTest {
    private BinaryFileRepository<Pacient> repo;

    @BeforeEach
    void setUp() {
        repo = new BinaryFileRepository<>("test/cabinet/data/test_patients.bin");
        repo.resetFile();
    }

    @Test
    void testAddAndFindAll() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        repo.add(pacient);
        List<Pacient> pacienti = repo.findAll();
        assertEquals(1, pacienti.size());
        assertEquals(pacient, pacienti.get(0));
    }

    @Test
    void testDuplicateId() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        repo.add(pacient);
        assertThrows(RuntimeException.class, () -> repo.add(pacient));
    }

    @Test
    void testDelete() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        repo.add(pacient);
        repo.delete(1);
        assertTrue(repo.findAll().isEmpty());
    }
}
