package cabinet.services;

import cabinet.domeniu.Pacient;
import cabinet.exceptions.NotFoundException;
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
        Pacient pacient = pacientRepository.find(id);
        if (pacient == null) {
            throw new NotFoundException("Pacientul cu ID-ul " + id + " nu a fost găsit.");
        }
        return pacient;
    }

    public List<Pacient> findAll() {
        return pacientRepository.findAll();
    }

    public void updatePacient(int id, Pacient pacientActualizat) {
        Pacient pacient = pacientRepository.find(id);
        if (pacient != null) {
            delete(id);
            pacientRepository.add(pacientActualizat);
        }
    }

    public void delete(int id) {
        pacientRepository.delete(id);
    }


}
