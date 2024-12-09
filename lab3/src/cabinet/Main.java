package cabinet;

import cabinet.config.ConfigLoader;
import cabinet.domeniu.Pacient;
import cabinet.domeniu.Programare;
import cabinet.config.RepositoryFactory;
import cabinet.repo.Repository;
import cabinet.services.PacientService;
import cabinet.services.ProgramareService;
import cabinet.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        try {
            // Încarcă setările din fișier
            ConfigLoader config = new ConfigLoader("lab3/src/cabinet/data/settings.properties");

            // Citește setările
            String repositoryType = config.getProperty("repository.type", "text");
            String patientsPath = config.getProperty("patients.path", "lab3/src/cabinet/data/patients.txt");
            String appointmentsPath = config.getProperty("appointments.path", "lab3/src/cabinet/data/appointments.txt");

            // Creează repository-uri
            Repository<Pacient> pacientRepository = RepositoryFactory.createPacientRepository(repositoryType, patientsPath);
            Repository<Programare> programareRepository = RepositoryFactory.createProgramareRepository(repositoryType, appointmentsPath);

            // Creează servicii
            PacientService pacientService = new PacientService(pacientRepository);
            ProgramareService programareService = new ProgramareService(programareRepository);

            // Pornește aplicația
            ConsoleUI ui = new ConsoleUI(pacientService, programareService);
            ui.start();

        } catch (Exception e) {
            System.err.println("Eroare la inițializarea aplicației: " + e.getMessage());
        }
    }
}
