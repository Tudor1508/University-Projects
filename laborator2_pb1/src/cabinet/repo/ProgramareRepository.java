package cabinet.repo;

import cabinet.domeniu.Programare;
import cabinet.exceptions.DuplicateIDException;
import java.util.ArrayList;
import java.util.List;

public class ProgramareRepository implements Repository<Programare> {
    private List<Programare> programari = new ArrayList<>();

    @Override
    public void add(Programare programare) {
        if (programari.stream().anyMatch(p -> p.getId() == programare.getId())) {
            throw new DuplicateIDException("Programarea cu ID-ul " + programare.getId() + " există deja.");
        }
        // Verificare pentru suprapunerea programărilor
        for (Programare p : programari) {
            if (p.seSuprapune(programare)) {
                throw new RuntimeException("Programarea se suprapune cu o programare existentă.");
            }
        }
        programari.add(programare);
    }

    @Override
    public Programare find(int id) {
        return programari.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Programare> findAll() {
        return programari;
    }

    @Override
    public void delete(int id) {
        programari.removeIf(p -> p.getId() == id);
    }
}
