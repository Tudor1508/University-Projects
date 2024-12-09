package cabinet.services;

import cabinet.domeniu.Pacient;
import cabinet.domeniu.Programare;
import cabinet.repo.GenericRepository;
import cabinet.services.ProgramareService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramareServiceTest {

    private GenericRepository<Programare> repository;
    private ProgramareService service;

    @BeforeEach
    void setUp() {
        repository = new GenericRepository<>();
        service = new ProgramareService(repository);
    }

    @Test
    void testAddProgramare() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        Programare programare = new Programare(1, pacient, LocalDateTime.now(), "Consultatie");

        service.addProgramare(programare);

        List<Programare> programari = service.getAllProgramari();
        assertEquals(1, programari.size());
        assertEquals(programare, programari.get(0));
    }



    @Test
    void testFindProgramare() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        Programare programare = new Programare(1, pacient, LocalDateTime.now(), "Consultatie");

        service.addProgramare(programare);

        Programare found = service.findProgramare(1);
        assertEquals(programare, found);
    }

    @Test
    void testFindNonExistentProgramare() {
        assertThrows(RuntimeException.class, () -> service.findProgramare(99));
    }

    @Test
    void testDeleteProgramare() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        Programare programare = new Programare(1, pacient, LocalDateTime.now(), "Consultatie");

        service.addProgramare(programare);
        service.deleteProgramare(1);

        assertTrue(service.getAllProgramari().isEmpty());
    }

    @Test
    void testDeleteNonExistentProgramare() {
        assertThrows(RuntimeException.class, () -> service.deleteProgramare(99));
    }

    @Test
    void testUpdateProgramare() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        Programare programare = new Programare(1, pacient, LocalDateTime.now(), "Consultatie");

        service.addProgramare(programare);

        Programare updatedProgramare = new Programare(1, pacient, LocalDateTime.now().plusDays(1), "Control");
        service.updateProgramare(1, updatedProgramare);

        List<Programare> programari = service.getAllProgramari();
        assertEquals(1, programari.size());
        assertEquals(updatedProgramare, programari.get(0));
    }
}
