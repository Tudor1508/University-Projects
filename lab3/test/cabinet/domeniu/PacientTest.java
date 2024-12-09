import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import cabinet.domeniu.Pacient;

class PacientTest {

    @Test
    void testConstructorImplicit() {
        Pacient pacient = new Pacient();
        assertEquals(0, pacient.getId());
        assertEquals("", pacient.getNume());
        assertEquals("", pacient.getPrenume());
        assertEquals(0, pacient.getVarsta());
    }

    @Test
    void testConstructorCuParametri() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        assertEquals(1, pacient.getId());
        assertEquals("Ion", pacient.getNume());
        assertEquals("Popescu", pacient.getPrenume());
        assertEquals(30, pacient.getVarsta());
    }

    @Test
    void testToString() {
        Pacient pacient = new Pacient(1, "Ion", "Popescu", 30);
        String expected = "Pacient { ID=1, Nume=Ion, Prenume=Popescu, Varsta=30 }";
        assertEquals(expected, pacient.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Pacient pacient1 = new Pacient(1, "Ion", "Popescu", 30);
        Pacient pacient2 = new Pacient(1, "Ion", "Popescu", 30);
        assertEquals(pacient1, pacient2);
        assertEquals(pacient1.hashCode(), pacient2.hashCode());
    }
}
