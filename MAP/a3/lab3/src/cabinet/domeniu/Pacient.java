package cabinet.domeniu;

import java.io.Serializable;
import java.util.Objects;

public class Pacient extends Entitate implements Serializable {
    private static final long serialVersionUID = 1L; // Versiune serializabilă
    private String nume;
    private String prenume;
    private int varsta;

    // Constructor implicit
    public Pacient() {
        super(0); // ID implicit
        this.nume = "";
        this.prenume = "";
        this.varsta = 0;
    }

    // Constructor cu parametri
    public Pacient(int id, String nume, String prenume, int varsta) {
        super(id);
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    @Override
    public String toString() {
        return "Pacient { ID=" + id + ", Nume=" + nume + ", Prenume=" + prenume + ", Varsta=" + varsta + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pacient pacient = (Pacient) o;
        return id == pacient.id &&
                varsta == pacient.varsta &&
                Objects.equals(nume, pacient.nume) &&
                Objects.equals(prenume, pacient.prenume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, varsta);
    }
}
