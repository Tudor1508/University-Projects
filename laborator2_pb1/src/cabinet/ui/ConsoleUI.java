package cabinet.ui;

import cabinet.domeniu.Pacient;
import cabinet.domeniu.Programare;
import cabinet.repo.PacientRepository;
import cabinet.repo.ProgramareRepository;
import cabinet.services.PacientService;
import cabinet.services.ProgramareService;

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
    }

    public void start() {
        while (true) {
            System.out.println("Meniu:");
            System.out.println("1. Adaugă pacient");
            System.out.println("2. Listează pacienți");
            System.out.println("3. Adaugă programare");
            System.out.println("4. Listează programări");
            System.out.println("0. Ieșire");

            int optiune = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            switch (optiune) {
                case 1:
                    adaugaPacient();
                    break;
                case 2:
                    listeazaPacienti();
                    break;
                case 3:
                    adaugaProgramare();
                    break;
                case 4:
                    listeazaProgramari();
                    break;
                case 0:
                    System.out.println("Ieșire...");
                    return;
                default:
                    System.out.println("Opțiune invalidă! Încearcă din nou.");
            }
        }
    }

    private void adaugaPacient() {
        System.out.print("Introdu ID pacient: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumă newline

        System.out.print("Introdu nume pacient: ");
        String nume = scanner.nextLine();

        System.out.print("Introdu prenume pacient: ");
        String prenume = scanner.nextLine();

        System.out.print("Introdu vârstă pacient: ");
        int varsta = scanner.nextInt();

        Pacient pacient = new Pacient(id, nume, prenume, varsta);
        pacientService.addPacient(pacient);
        System.out.println("Pacient adăugat cu succes!");
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
        scanner.nextLine(); // Consumă newline

        System.out.print("Introdu ID pacient: ");
        int pacientId = scanner.nextInt();
        scanner.nextLine(); // Consumă newline

        System.out.print("Introdu data programării (format: YYYY-MM-DD): ");
        String dataString = scanner.nextLine();
        Date data = java.sql.Date.valueOf(dataString); // Convertește în Date

        System.out.print("Introdu scopul programării: ");
        String scop = scanner.nextLine();

        Pacient pacient = pacientService.find(pacientId);
        if (pacient == null) {
            System.out.println("Pacientul cu ID " + pacientId + " nu a fost găsit!");
            return;
        }

        Programare programare = new Programare(id, pacient, data, scop);
        programareService.addProgramare(programare);
        System.out.println("Programare adăugată cu succes!");
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
}
