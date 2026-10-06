package cabinet.services;

import cabinet.domeniu.Pacient;
import cabinet.repo.TextFileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PacientServiceTest {

    private PacientService pacientService;
    private final String testFilePath = "test_patients.txt";

    @BeforeEach
    void setUp() {
        // Inițializează repository-ul real pentru teste
        TextFileRepository<Pacient> pacientRepository = new TextFileRepository<>(
                testFilePath,
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
        pacientService = new PacientService(pacientRepository);
    }

    @AfterEach
    void tearDown() {
        // Șterge fișierul de test pentru a curăța datele
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void testAddPacient() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        pacientService.addPacient(pacient);

        List<Pacient> pacienti = pacientService.getAllPacienti();
        assertEquals(1, pacienti.size());
        assertEquals(pacient, pacienti.get(0));
    }

    @Test
    void testAddPacientDuplicateId() {
        Pacient pacient1 = new Pacient(1, "Ion", "Popescu", 30);
        Pacient pacient2 = new Pacient(1, "Maria", "Ionescu", 25);

        pacientService.addPacient(pacient1);
        assertThrows(RuntimeException.class, () -> pacientService.addPacient(pacient2));
    }

    @Test
    void testFindPacient() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        pacientService.addPacient(pacient);

        Pacient found = pacientService.findPacient(1);
        assertEquals(pacient, found);
    }

    @Test
    void testFindPacientNotFound() {
        assertThrows(RuntimeException.class, () -> pacientService.findPacient(99));
    }

    @Test
    void testDeletePacient() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        pacientService.addPacient(pacient);

        pacientService.deletePacient(1);
        assertTrue(pacientService.getAllPacienti().isEmpty());
    }

    @Test
    void testDeletePacientNotFound() {
        assertThrows(RuntimeException.class, () -> pacientService.deletePacient(99));
    }
}
