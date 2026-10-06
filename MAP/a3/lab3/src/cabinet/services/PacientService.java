package cabinet.services;

import cabinet.domeniu.Pacient;
import cabinet.exceptions.DuplicateIDException;
import cabinet.repo.Repository;

import java.util.List;

public class PacientService {
    private Repository<Pacient> repository;

    public PacientService(Repository<Pacient> repository) {
        this.repository = repository;
    }

    public void addPacient(Pacient pacient) {
        try {
            repository.add(pacient);
            System.out.println("Pacient adăugat cu succes: " + pacient);
        } catch (DuplicateIDException e) {
            System.err.println("Eroare: " + e.getMessage());
        }
    }

    public Pacient findPacient(int id) {
        return repository.find(id);
    }

    public List<Pacient> getAllPacienti() {
        return repository.findAll();
    }

    public void deletePacient(int id) {
        repository.delete(id);
    }

    public void updatePacient(int id, Pacient pacientActualizat) {
        deletePacient(id);
        addPacient(pacientActualizat);
    }
}
