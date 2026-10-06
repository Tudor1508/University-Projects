package cabinet.repo;

import cabinet.domeniu.Pacient;
import cabinet.exceptions.DuplicateIDException;
import java.util.ArrayList;
import java.util.List;

public class PacientRepository implements Repository<Pacient> {
    private List<Pacient> pacienti = new ArrayList<>();

    @Override
    public void add(Pacient pacient) {
        if (pacienti.stream().anyMatch(p -> p.getId() == pacient.getId())) {
            throw new DuplicateIDException("Pacientul cu ID-ul " + pacient.getId() + " există deja.");
        }
        pacienti.add(pacient);
    }

    @Override
    public Pacient find(int id) {
        return pacienti.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Pacient> findAll() {
        return pacienti;
    }

    @Override
    public void delete(int id) {
        pacienti.removeIf(p -> p.getId() == id);
    }
}
