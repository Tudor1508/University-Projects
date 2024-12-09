package cabinet.domeniu;

import java.io.Serializable;

public abstract class Entitate implements Serializable {
    private static final long serialVersionUID = 1L; // Versiune serializabilă
    protected int id;

    public Entitate(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID=" + id;
    }
}
