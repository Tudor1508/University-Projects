package cabinet.ui;

import cabinet.domeniu.Pacient;
import cabinet.domeniu.Programare;
import cabinet.repo.GenericRepository;
import cabinet.services.PacientService;
import cabinet.services.ProgramareService;
import cabinet.ui.ConsoleUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleUITest {

    private PacientService pacientService;
    private ProgramareService programareService;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        // Folosim repository-uri generice pentru testare
        pacientService = new PacientService(new GenericRepository<>());
        programareService = new ProgramareService(new GenericRepository<>());

        // Capturăm outputul în loc să îl trimitem la consolă
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testAdaugaPacient() {
        String input = "1\nIon\nPopescu\n30\n"; // Simulăm inputul utilizatorului
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ConsoleUI ui = new ConsoleUI(pacientService, programareService);
        ui.adaugaPacient();

        assertEquals(1, pacientService.getAllPacienti().size());
        Pacient pacient = pacientService.getAllPacienti().get(0);
        assertEquals(1, pacient.getId());
        assertEquals("Ion", pacient.getNume());
        assertEquals("Popescu", pacient.getPrenume());
        assertEquals(30, pacient.getVarsta());

        assertTrue(outputStream.toString().contains("Pacient adăugat cu succes!"));
    }

    @Test
    void testListeazaPacienti() {
        // Adăugăm pacienți direct în repository
        pacientService.addPacient(new Pacient(1, "Ion", "Popescu", 30));
        pacientService.addPacient(new Pacient(2, "Maria", "Ionescu", 25));

        ConsoleUI ui = new ConsoleUI(pacientService, programareService);
        ui.listeazaPacienti();

        String output = outputStream.toString();
        assertTrue(output.contains("Pacient { ID=1, Nume=Ion, Prenume=Popescu, Varsta=30 }"));
        assertTrue(output.contains("Pacient { ID=2, Nume=Maria, Prenume=Ionescu, Varsta=25 }"));
    }

    @Test
    void testAdaugaProgramare() {
        // Adăugăm un pacient necesar pentru programare
        pacientService.addPacient(new Pacient(1, "Ion", "Popescu", 30));

        String input = "1\n1\n2024-01-01 10:00\nConsultatie\n"; // Simulăm inputul utilizatorului
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ConsoleUI ui = new ConsoleUI(pacientService, programareService);
        ui.adaugaProgramare();

        assertEquals(1, programareService.getAllProgramari().size());
        Programare programare = programareService.getAllProgramari().get(0);
        assertEquals(1, programare.getId());
        assertEquals("Consultatie", programare.getScop());
        assertEquals(1, programare.getPacient().getId());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), programare.getData());

        assertTrue(outputStream.toString().contains("Programare adăugată cu succes!"));
    }

    @Test
    void testListeazaProgramari() {
        // Adăugăm un pacient și o programare direct în repository
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        pacientService.addPacient(pacient);
        programareService.addProgramare(new Programare(1, pacient,
                LocalDateTime.of(2024, 1, 1, 10, 0), "Consultatie"));

        ConsoleUI ui = new ConsoleUI(pacientService, programareService);
        ui.listeazaProgramari();

        String output = outputStream.toString();
        assertTrue(output.contains("Programare { ID=1, Pacient=1, Data=2024-01-01T10:00, Scop=Consultatie }"));
    }
}
