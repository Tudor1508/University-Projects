package cabinet.repo;

import cabinet.domeniu.Pacient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextFileRepositoryTest {
    private static final String TEST_FILE_PATH = "test/test_patients.txt";
    private TextFileRepository<Pacient> repository;

    @BeforeEach
    void setUp() throws IOException {
        // Creează directorul și fișierul
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
        testFile.getParentFile().mkdirs();
        testFile.createNewFile();

        // Scrie date inițiale în fișier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("Pacient { ID=1, Nume=Ion, Prenume=Popescu, Varsta=30 }");
            writer.newLine();
            writer.write("Pacient { ID=2, Nume=Maria, Prenume=Ionescu, Varsta=25 }");
            writer.newLine();
        }

        // Inițializează repository-ul
        repository = new TextFileRepository<>(
                TEST_FILE_PATH,
                line -> {
                    String[] parts = line.replace("Pacient { ", "").replace(" }", "").split(", ");
                    int id = Integer.parseInt(parts[0].split("=")[1]);
                    String nume = parts[1].split("=")[1];
                    String prenume = parts[2].split("=")[1];
                    int varsta = Integer.parseInt(parts[3].split("=")[1]);
                    return new Pacient(id, nume, prenume, varsta);
                },
                pacient -> String.format(
                        "Pacient { ID=%d, Nume=%s, Prenume=%s, Varsta=%d }",
                        pacient.getId(), pacient.getNume(), pacient.getPrenume(), pacient.getVarsta()
                )
        );
    }

    @AfterEach
    void tearDown() {
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void testFindAll() {
        List<Pacient> patients = repository.findAll();
        assertEquals(2, patients.size());
        assertEquals("Ion", patients.get(0).getNume());
    }

    @Test
    void testAddAndFindAll() {
        repository.add(new Pacient(3, "Andrei", "Vasilescu", 40));
        List<Pacient> patients = repository.findAll();
        assertEquals(3, patients.size());
        assertEquals("Andrei", patients.get(2).getNume());
    }

    @Test
    void testFind() {
        Pacient pacient = repository.find(1);
        assertEquals("Ion", pacient.getNume());
    }

    @Test
    void testDelete() {
        repository.delete(1);
        List<Pacient> patients = repository.findAll();
        assertEquals(1, patients.size());
    }

    @Test
    void testDuplicateId() {
        assertThrows(RuntimeException.class, () -> repository.add(new Pacient(1, "Duplicate", "Pacient", 30)));
    }
}
