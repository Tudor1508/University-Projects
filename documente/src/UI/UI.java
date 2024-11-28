package UI;

import Domain.Document;
import Domain.Manuscript;
import Domain.Presentation;
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
        System.out.println("4. Salvare documente conforme în fișier");
        System.out.println("5. Ștergere document");
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
                case 4 -> saveConformingDocumentsToFile();
                case 5 -> deleteDocument();
                case 0 -> {
                    System.out.println("Ieșire din aplicație.");
                    return;
                }
                default -> System.out.println("Opțiune invalidă. Încercați din nou.");
            }
        }
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

            Manuscript manuscript = new Manuscript(author, numberOfWords, numberOfPages);
            service.add(manuscript);
            System.out.println("Manuscript adăugat cu succes!");
        } else if (type.equalsIgnoreCase("Presentation")) {
            System.out.print("Introduceți autorul: ");
            String author = scanner.nextLine();
            System.out.print("Introduceți numărul de slide-uri: ");
            int numberOfSlides = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Introduceți textul prezentării: ");
            String text = scanner.nextLine();

            Presentation presentation = new Presentation(author, numberOfSlides, text);
            service.add(presentation);
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
        List<Document> documents = service.getAllEntities();
        documents.stream()
                .filter(doc -> !doc.isConformant())
                .forEach(System.out::println);
    }

    private void saveConformingDocumentsToFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți numele fișierului unde să salvați documentele conforme: ");
        String fileName = scanner.nextLine();
        service.fisierText();
        System.out.println("Documentele conforme au fost salvate în fișier.");
    }

    private void deleteDocument() {
        displayAllDocuments();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți indexul documentului pe care doriți să îl ștergeți: ");
        int index = scanner.nextInt();
        try {
            service.getAllEntities().remove(index - 1);
            System.out.println("Documentul a fost șters cu succes.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index invalid. Încercați din nou.");
        }
    }
}
