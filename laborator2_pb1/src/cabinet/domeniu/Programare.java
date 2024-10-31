package cabinet.domeniu;

import java.time.LocalDateTime;

public class Programare {
    private int id;
    private Pacient pacient;
    private LocalDateTime data; // Folosim LocalDateTime pentru a include atât data, cât și ora
    private String scop;

    public Programare(int id, Pacient pacient, LocalDateTime data, String scop) {
        this.id = id;
        this.pacient = pacient;
        this.data = data;
        this.scop = scop;
    }

    public int getId() { return id; }
    public Pacient getPacient() { return pacient; }
    public LocalDateTime getData() { return data; }
    public String getScop() { return scop; }

    // Metoda pentru a verifica dacă o altă programare se suprapune
    public boolean seSuprapune(Programare altaProgramare) {
        LocalDateTime endTime = this.data.plusHours(1); // Durata programării de 1 oră
        LocalDateTime altaEndTime = altaProgramare.getData().plusHours(1);

        // Verifică dacă programările se suprapun
        return this.data.isBefore(altaEndTime) && altaProgramare.getData().isBefore(endTime);
    }
}
