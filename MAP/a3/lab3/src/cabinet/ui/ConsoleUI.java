package cabinet.ui;

import cabinet.domeniu.Pacient;
import cabinet.domeniu.Programare;
import cabinet.services.PacientService;
import cabinet.services.ProgramareService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleUI {
    private PacientService pacientService;
    private ProgramareService programareService;
    private Scanner scanner;

    public ConsoleUI(PacientService pacientService, ProgramareService programareService) {
        this.pacientService = pacientService;
        this.programareService = programareService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Meniu:");
            System.out.println("1. Adaugă pacient");
            System.out.println("2. Listează pacienți");
            System.out.println("3. Șterge pacient");
            System.out.println("4. Adaugă programare");
            System.out.println("5. Listează programări");
            System.out.println("6. Șterge programare");
            System.out.println("0. Ieșire");

            int optiune = scanner.nextInt();
            scanner.nextLine(); // Consumă linia rămasă

            switch (optiune) {
                case 1 -> adaugaPacient();
                case 2 -> listeazaPacienti();
                case 3 -> stergePacient();
                case 4 -> adaugaProgramare();
                case 5 -> listeazaProgramari();
                case 6 -> stergeProgramare();
                case 0 -> {
                    System.out.println("Ieșire...");
                    return;
                }
                default -> System.out.println("Opțiune invalidă!");
            }
        }
    }

    public void adaugaPacient() {
        System.out.print("Introdu ID pacient: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Introdu nume: ");
        String nume = scanner.nextLine();

        System.out.print("Introdu prenume: ");
        String prenume = scanner.nextLine();

        System.out.print("Introdu vârstă: ");
        int varsta = scanner.nextInt();

        Pacient pacient = new Pacient(id, nume, prenume, varsta);
        pacientService.addPacient(pacient);
        System.out.println("Pacient adăugat cu succes!");
    }

    public void listeazaPacienti() {
        pacientService.getAllPacienti().forEach(System.out::println);
    }

    private void stergePacient() {
        System.out.print("Introdu ID-ul pacientului de șters: ");
        int id = scanner.nextInt();
        pacientService.deletePacient(id);
        System.out.println("Pacient șters cu succes!");
    }

    public void adaugaProgramare() {
        System.out.print("Introdu ID programare: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Introdu ID pacient: ");
        int pacientId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Introdu data programării (YYYY-MM-DD HH:mm): ");
        String dataString = scanner.nextLine();

        System.out.print("Introdu scopul programării: ");
        String scop = scanner.nextLine();

        LocalDateTime data = LocalDateTime.parse(dataString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Programare programare = new Programare(id, pacientService.findPacient(pacientId), data, scop);
        programareService.addProgramare(programare);
        System.out.println("Programare adăugată cu succes!");
    }

    public void listeazaProgramari() {
        programareService.getAllProgramari().forEach(System.out::println);
    }

    private void stergeProgramare() {
        System.out.print("Introdu ID-ul programării de șters: ");
        int id = scanner.nextInt();
        programareService.deleteProgramare(id);
        System.out.println("Programare ștearsă cu succes!");
    }
}
