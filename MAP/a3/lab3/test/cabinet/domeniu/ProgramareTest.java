package cabinet.domeniu;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import cabinet.domeniu.Programare;
import cabinet.domeniu.Pacient;

class ProgramareTest {

    @Test
    void testConstructor() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        LocalDateTime data = LocalDateTime.now();
        Programare programare = new Programare(1, pacient, data, "Control stomatologic");

        assertEquals(1, programare.getId());
        assertEquals(pacient, programare.getPacient());
        assertEquals(data, programare.getData());
        assertEquals("Control stomatologic", programare.getScop());
    }

    @Test
    void testToString() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        LocalDateTime data = LocalDateTime.of(2024, 1, 1, 10, 0);
        Programare programare = new Programare(1, pacient, data, "Control stomatologic");

        String expected = "Programare { ID=1, Pacient=1, Data=2024-01-01T10:00, Scop=Control stomatologic }";
        assertEquals(expected, programare.toString());
    }

    @Test
    void testProgramareWithNullPacient() {
        assertThrows(NullPointerException.class, () -> {
            new Programare(1, null, LocalDateTime.now(), "Control stomatologic");
        });
    }
}
