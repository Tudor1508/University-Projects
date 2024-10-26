package cabinet.domeniu;

import java.util.Date;

public class Programare {
    private int id;
    private Pacient pacient;
    private Date data;
    private String scop;

    public Programare(int id, Pacient pacient, Date data, String scop) {
        this.id = id;
        this.pacient = pacient;
        this.data = data;
        this.scop = scop;
    }
    public int getId() { return id; }
    public Pacient getPacient() { return pacient; }
    public Date getData() { return data; }
    public String getScop() { return scop; }
}
