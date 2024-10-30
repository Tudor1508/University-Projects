package cabinet.services;

import cabinet.domeniu.Pacient;
import cabinet.repo.PacientRepository;

import java.util.List;

public class PacientService {
    private PacientRepository pacientRepository;

    public PacientService(PacientRepository pacientRepository) {
        this.pacientRepository = pacientRepository;
    }

    public void addPacient(Pacient pacient) {
        pacientRepository.add(pacient);
    }

    public Pacient find(int id) {
        return pacientRepository.find(id);
    }

    public List<Pacient> findAll() {
        return pacientRepository.findAll();
    }

    public void updatePacient(int id, Pacient pacientActualizat) {
        Pacient pacient = pacientRepository.find(id);
        if (pacient != null) {
            delete(id); // Ștergem pacientul vechi
            pacientRepository.add(pacientActualizat); // Adăugăm pacientul actualizat
        }
    }

    public void delete(int id) {
        pacientRepository.delete(id);
    }


}
