package cabinet.config;


import cabinet.domeniu.Pacient;
import cabinet.domeniu.Programare;
import cabinet.repo.BinaryFileRepository;
import cabinet.repo.Repository;
import cabinet.repo.TextFileRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RepositoryFactory {
    public static Repository<Pacient> createPacientRepository(String repositoryType, String filePath) {
        if ("binary".equalsIgnoreCase(repositoryType)) {
            return new BinaryFileRepository<>(filePath);
        } else if ("text".equalsIgnoreCase(repositoryType)) {
            return new TextFileRepository<>(
                    filePath,
                    line -> {
                        String[] parts = line.replace("Pacient { ", "").replace(" }", "").split(", ");
                        int id = Integer.parseInt(parts[0].split("=")[1]);
                        String nume = parts[1].split("=")[1];
                        String prenume = parts[2].split("=")[1];
                        int varsta = Integer.parseInt(parts[3].split("=")[1]);
                        return new Pacient(id, nume, prenume, varsta);
                    },
                    pacient -> String.format(
                            "Pacient { ID=%d, Nume=%s, Prenume=%s, Varsta=%d }",
                            pacient.getId(), pacient.getNume(), pacient.getPrenume(), pacient.getVarsta()
                    )
            );
        } else {
            throw new IllegalArgumentException("Tipul repository-ului pentru Pacient nu este suportat: " + repositoryType);
        }
    }

    public static Repository<Programare> createProgramareRepository(String repositoryType, String filePath) {
        if ("binary".equalsIgnoreCase(repositoryType)) {
            return new BinaryFileRepository<>(filePath);
        } else if ("text".equalsIgnoreCase(repositoryType)) {
            return new TextFileRepository<>(
                    filePath,
                    line -> {
                        String[] parts = line.replace("Programare { ", "").replace(" }", "").split(", ");
                        int id = Integer.parseInt(parts[0].split("=")[1]);
                        int pacientId = Integer.parseInt(parts[1].split("=")[1]);
                        LocalDateTime data = LocalDateTime.parse(parts[2].split("=")[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        String scop = parts[3].split("=")[1];
                        return new Programare(id, new Pacient(pacientId, "", "", 0), data, scop);
                    },
                    programare -> String.format(
                            "Programare { ID=%d, Pacient=%d, Data=%s, Scop=%s }",
                            programare.getId(),
                            programare.getPacient().getId(),
                            programare.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                            programare.getScop()
                    )
            );
        } else {
            throw new IllegalArgumentException("Tipul repository-ului pentru Programare nu este suportat: " + repositoryType);
        }
    }
}
