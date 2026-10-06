package UI;

import Domain.Document;
import Service.Service;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final Service service;

    public UI(Service service) {
        this.service = service;
    }

    private void showOptions() {
        System.out.println("1. Adăugare document (Manuscript/Presentation)");
        System.out.println("2. Afișare toate documentele");
        System.out.println("3. Afișare documente neconforme");
        System.out.println("4. Afișare documente neconforme ordonate după autor");
        System.out.println("5. Salvare documente conforme în fișier");
        System.out.println("0. Ieșire");
    }

    public void ShowUserInterface() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showOptions();
            System.out.print("Alege o opțiune: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addDocument();
                case 2 -> displayAllDocuments();
                case 3 -> displayNonConformingDocuments();
                case 4 -> displaySortedNonConformingDocuments();
                case 5 -> saveConformingDocumentsToFile();
                case 0 -> {
                    System.out.println("Ieșire din aplicație.");
                    return;
                }
                default -> System.out.println("Opțiune invalidă. Încercați din nou.");
            }
        }
    }

    private void displaySortedNonConformingDocuments() {
        System.out.println("Documentele neconforme ordonate după autor:");
        service.getSortedNonConformingDocuments().forEach(System.out::println);
    }

    private void addDocument() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți tipul documentului (Manuscript/Presentation): ");
        String type = scanner.nextLine();

        if (type.equalsIgnoreCase("Manuscript")) {
            System.out.print("Introduceți autorul: ");
            String author = scanner.nextLine();
            System.out.print("Introduceți numărul de cuvinte: ");
            int numberOfWords = scanner.nextInt();
            System.out.print("Introduceți numărul de pagini: ");
            int numberOfPages = scanner.nextInt();

            service.add(new Domain.Manuscript(author, numberOfWords, numberOfPages));
            System.out.println("Manuscript adăugat cu succes!");
        } else if (type.equalsIgnoreCase("Presentation")) {
            System.out.print("Introduceți autorul: ");
            String author = scanner.nextLine();
            System.out.print("Introduceți numărul de slide-uri: ");
            int numberOfSlides = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            System.out.print("Introduceți textul prezentării: ");
            String text = scanner.nextLine();

            service.add(new Domain.Presentation(author, numberOfSlides, text));
            System.out.println("Presentation adăugat cu succes!");
        } else {
            System.out.println("Tip invalid. Introduceți Manuscript sau Presentation.");
        }
    }

    private void displayAllDocuments() {
        System.out.println("Toate documentele:");
        List<Document> documents = service.getAllEntities();
        if (documents.isEmpty()) {
            System.out.println("Nu există documente înregistrate.");
        } else {
            documents.forEach(System.out::println);
        }
    }

    private void displayNonConformingDocuments() {
        System.out.println("Documentele neconforme:");
        service.getAllEntities().stream()
                .filter(doc -> !doc.isConformant())
                .forEach(System.out::println);
    }

    private void saveConformingDocumentsToFile() {
        service.fisierText();
        System.out.println("Documentele conforme au fost salvate în fișier.");
    }

}
