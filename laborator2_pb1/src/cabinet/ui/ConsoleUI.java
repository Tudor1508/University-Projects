package cabinet.ui;

import cabinet.domeniu.Pacient;
import cabinet.domeniu.Programare;
import cabinet.exceptions.DuplicateIDException;
import cabinet.exceptions.NotFoundException;
import cabinet.exceptions.OverlappingAppointmentException;
import cabinet.repo.PacientRepository;
import cabinet.repo.ProgramareRepository;
import cabinet.services.PacientService;
import cabinet.services.ProgramareService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private PacientService pacientService;
    private ProgramareService programareService;
    private Scanner scanner;

    public ConsoleUI() {
        PacientRepository pacientRepo = new PacientRepository();
        ProgramareRepository programareRepo = new ProgramareRepository();
        pacientService = new PacientService(pacientRepo);
        programareService = new ProgramareService(programareRepo);
        scanner = new Scanner(System.in);

        Pacient pacient1 = new Pacient(1, "Ion", "Popescu", 30);
        Pacient pacient2 = new Pacient(2, "Maria", "Ionescu", 25);
        Pacient pacient3 = new Pacient(3, "Andrei", "Vasilescu", 40);


        pacientService.addPacient(pacient1);
        pacientService.addPacient(pacient2);
        pacientService.addPacient(pacient3);

        // Adaugă programări predefinite
        Programare programare1 = new Programare(1, pacient1, LocalDateTime.of(2024, 10, 31, 9, 0), "Control general");
        Programare programare2 = new Programare(2, pacient2, LocalDateTime.of(2024, 10, 31, 10, 0), "Consultație dermatologică");
        Programare programare3 = new Programare(3, pacient3, LocalDateTime.of(2024, 10, 31, 11, 0), "Consultație cardiologică");

        programareService.addProgramare(programare1);
        programareService.addProgramare(programare2);
        programareService.addProgramare(programare3);
    }

    public void start() {
        while (true) {
            System.out.println("Meniu:");
            System.out.println("1. Adaugă pacient");
            System.out.println("2. Listează pacienți");
            System.out.println("3. Adaugă programare");
            System.out.println("4. Listează programări");
            System.out.println("5. Actualizează pacient");
            System.out.println("6. Șterge pacient");
            System.out.println("7. Actualizează programare");
            System.out.println("8. Șterge programare");
            System.out.println("0. Ieșire");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1 -> adaugaPacient();
                case 2 -> listeazaPacienti();
                case 3 -> adaugaProgramare();
                case 4 -> listeazaProgramari();
                case 5 -> updatePacient();
                case 6 -> deletePacient();
                case 7 -> updateProgramare();
                case 8 -> deleteProgramare();
                case 0 -> {
                    System.out.println("Ieșire...");
                    return;
                }
                default -> System.out.println("Opțiune invalidă! Încearcă din nou.");
            }
        }
    }

    private void adaugaPacient() {
        try {
            System.out.print("Introdu ID pacient: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Introdu nume pacient: ");
            String nume = scanner.nextLine();

            System.out.print("Introdu prenume pacient: ");
            String prenume = scanner.nextLine();

            System.out.print("Introdu vârstă pacient: ");
            int varsta = scanner.nextInt();

            Pacient pacient = new Pacient(id, nume, prenume, varsta);
            pacientService.addPacient(pacient);
            System.out.println("Pacient adăugat cu succes!");
        } catch (DuplicateIDException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void listeazaPacienti() {
        List<Pacient> pacienti = pacientService.findAll();
        if (pacienti.isEmpty()) {
            System.out.println("Nu există pacienți.");
        } else {
            for (Pacient pacient : pacienti) {
                System.out.println("ID: " + pacient.getId() + ", Nume: " + pacient.getNume() +
                        ", Prenume: " + pacient.getPrenume() + ", Vârstă: " + pacient.getVarsta());
            }
        }
    }

    private void adaugaProgramare() {
        System.out.print("Introdu ID programare: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Introdu ID pacient: ");
        int pacientId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Introdu data și ora programării (format: YYYY-MM-DD HH:mm): ");
        String dataString = scanner.nextLine();
        LocalDateTime data = LocalDateTime.parse(dataString); // Convertește în LocalDateTime

        System.out.print("Introdu scopul programării: ");
        String scop = scanner.nextLine();

        Pacient pacient = pacientService.find(pacientId);
        if (pacient == null) {
            System.out.println("Pacientul cu ID " + pacientId + " nu a fost găsit!");
            return;
        }

        Programare programare = new Programare(id, pacient, data, scop);
        try {
            programareService.addProgramare(programare);
            System.out.println("Programare adăugată cu succes!");
        } catch (RuntimeException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }


    private void listeazaProgramari() {
        List<Programare> programari = programareService.findAll();
        if (programari.isEmpty()) {
            System.out.println("Nu există programări.");
        } else {
            for (Programare programare : programari) {
                System.out.println("ID: " + programare.getId() + ", Pacient: " + programare.getPacient().getNume() +
                        " " + programare.getPacient().getPrenume() + ", Data: " + programare.getData() +
                        ", Scop: " + programare.getScop());
            }
        }
    }

    private void updatePacient() {
        try {
            System.out.print("Introdu ID pacient de actualizat: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Pacient pacient = pacientService.find(id);
            if (pacient == null) throw new NotFoundException("Pacientul cu ID " + id + " nu a fost găsit!");

            System.out.print("Introdu noul nume pacient: ");
            String nume = scanner.nextLine();

            System.out.print("Introdu noul prenume pacient: ");
            String prenume = scanner.nextLine();

            System.out.print("Introdu noua vârstă pacient: ");
            int varsta = scanner.nextInt();

            Pacient pacientActualizat = new Pacient(id, nume, prenume, varsta);
            pacientService.updatePacient(id, pacientActualizat);
            System.out.println("Pacient actualizat cu succes!");
        } catch (NotFoundException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void deletePacient() {
        try {
            System.out.print("Introdu ID pacient de șters: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            pacientService.delete(id);
            System.out.println("Pacient șters cu succes!");
        } catch (NotFoundException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void updateProgramare() {
        try {
            System.out.print("Introdu ID programare de actualizat: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Programare programare = programareService.find(id);
            if (programare == null) throw new NotFoundException("Programarea cu ID " + id + " nu a fost găsită!");

            System.out.print("Introdu noua dată și oră a programării (format: YYYY-MM-DD HH:mm): ");
            String dataString = scanner.nextLine();

            // Debug: verificăm ce dată a fost introdusă
            System.out.println("Data introdusă: " + dataString);

            // Formatarea datei și orei
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime data = LocalDateTime.parse(dataString, formatter);

            System.out.print("Introdu noul scop al programării: ");
            String scop = scanner.nextLine();

            Programare programareActualizata = new Programare(id, programare.getPacient(), data, scop);
            programareService.updateProgramare(id, programareActualizata);
            System.out.println("Programare actualizată cu succes!");
        } catch (NotFoundException e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Eroare: Formatul datei nu este corect. Folosește formatul YYYY-MM-DD HH:mm.");
        } catch (OverlappingAppointmentException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void deleteProgramare() {
        try {
            System.out.print("Introdu ID programare de șters: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            programareService.delete(id);
            System.out.println("Programare ștearsă cu succes!");
        } catch (NotFoundException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}
