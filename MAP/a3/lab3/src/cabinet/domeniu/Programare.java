package cabinet.domeniu;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Programare extends Entitate implements Serializable {
    private static final long serialVersionUID = 1L;
    private Pacient pacient;
    private LocalDateTime data;
    private String scop;

    public Programare(int id, Pacient pacient, LocalDateTime data, String scop) {
        super(id);
        if (pacient == null) {
            throw new NullPointerException("Pacientul nu poate fi null.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data nu poate fi null.");
        }
        if (scop == null || scop.trim().isEmpty()) {
            throw new IllegalArgumentException("Scopul nu poate fi gol.");
        }
        this.pacient = pacient;
        this.data = data;
        this.scop = scop;
    }


    public Pacient getPacient() {
        return pacient;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getScop() {
        return scop;
    }

    @Override
    public String toString() {
        return "Programare { ID=" + id + ", Pacient=" + pacient.getId() + ", Data=" + data + ", Scop=" + scop + " }";
    }
}
