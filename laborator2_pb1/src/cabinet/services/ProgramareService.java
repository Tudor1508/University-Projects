package cabinet.services;

import cabinet.domeniu.Programare;
import cabinet.repo.ProgramareRepository;

import java.util.List;

public class ProgramareService {
    private ProgramareRepository programareRepository;

    public ProgramareService(ProgramareRepository programareRepository) {
        this.programareRepository = programareRepository;
    }

    public void addProgramare(Programare programare) {
        programareRepository.add(programare);
    }

    public Programare find(int id) {
        return programareRepository.find(id);
    }

    public List<Programare> findAll() {
        return programareRepository.findAll();
    }

    public void updateProgramare(int id, Programare programareActualizata) {
        Programare programare = programareRepository.find(id);
        if (programare != null) {
            delete(id); // Ștergem programarea veche
            programareRepository.add(programareActualizata); // Adăugăm programarea actualizată
        }
    }

    public void delete(int id) {
        programareRepository.delete(id);
    }

}
