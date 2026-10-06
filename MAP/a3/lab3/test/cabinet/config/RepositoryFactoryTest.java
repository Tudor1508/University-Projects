package cabinet.config;

import cabinet.config.RepositoryFactory;
import cabinet.domeniu.Pacient;
import cabinet.domeniu.Programare;
import cabinet.repo.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryFactoryTest {

    @BeforeEach
    void setUp() {
        // Curățăm fișierele de test înainte de fiecare test
        new File("test-pacienti.txt").delete();
        new File("test-pacienti.bin").delete();
        new File("test-programari.txt").delete();
        new File("test-programari.bin").delete();
    }

    @Test
    void testCreatePacientTextRepository() {
        String filePath = "test-pacienti.txt";
        Repository<Pacient> repository = RepositoryFactory.createPacientRepository("text", filePath);

        assertNotNull(repository);

        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        repository.add(pacient);

        List<Pacient> pacienti = repository.findAll();
        assertEquals(1, pacienti.size());
        assertEquals(1, pacienti.get(0).getId());
        assertEquals("Ion", pacienti.get(0).getNume());
    }

    @Test
    void testCreateProgramareBinaryRepository() {
        String filePath = "test-programari.bin";
        Repository<Programare> repository = RepositoryFactory.createProgramareRepository("binary", filePath);

        assertNotNull(repository);

        Programare programare = new Programare(1, new Pacient(1, "Ion", "Popescu", 30),
                LocalDateTime.of(2024, 1, 1, 10, 0), "Consultatie");
        repository.add(programare);

        List<Programare> programari = repository.findAll();
        assertEquals(1, programari.size());
        assertEquals(1, programari.get(0).getId());
        assertEquals("Consultatie", programari.get(0).getScop());
    }

    @Test
    void testInvalidRepositoryType() {
        String filePath = "invalid-repo.txt";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                RepositoryFactory.createPacientRepository("invalid", filePath));
        assertEquals("Tipul repository-ului pentru Pacient nu este suportat: invalid", exception.getMessage());
    }
}
