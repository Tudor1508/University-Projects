package cabinet.services;

import cabinet.domeniu.Programare;
import cabinet.exceptions.DuplicateIDException;
import cabinet.repo.Repository;

import java.util.List;

public class ProgramareService {
    private Repository<Programare> repository;

    public ProgramareService(Repository<Programare> repository) {
        this.repository = repository;
    }

    public void addProgramare(Programare programare) {
        try {
            repository.add(programare);
            System.out.println("Programare adăugată cu succes: " + programare);
        } catch (DuplicateIDException e) {
            System.err.println("Eroare: " + e.getMessage());
        }
    }

    public Programare findProgramare(int id) {
        return repository.find(id);
    }

    public List<Programare> getAllProgramari() {
        return repository.findAll();
    }

    public void deleteProgramare(int id) {
        repository.delete(id);
    }

    public void updateProgramare(int id, Programare programareActualizata) {
        deleteProgramare(id);
        addProgramare(programareActualizata);
    }
}
