package Service;

import Domain.Document;
import Domain.Manuscript;
import Domain.Presentation;
import Repository.Repository;

import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.Arrays;

public class Service {
    Repository repository;
    private String numeFisier;
    private String locatieFisier;

    public Service(Repository repository) {
        this.repository = repository;
        loadSettings();
        initFromFile();
    }

    private void loadSettings() {
        try (InputStream input = new FileInputStream("settings.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            numeFisier = prop.getProperty("nume_fisier");
            locatieFisier = prop.getProperty("locatie_fisier");
            System.out.println("Fișierul este: " + numeFisier);
            System.out.println("Locația fișierului este: " + locatieFisier);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void add(Document document) {
        repository.add(document);
    }

    public List<Document> getAllEntities() {
        return repository.getAllEntities();
    }

    public void fisierText() {
        List<Document> entitati = repository.getAllEntities();

        List<Document> entitatiFiltrateSiSortate =
                entitati.stream().filter(p1 -> p1.isConformant()).sorted((p1, p2) -> (p1.getAuthor()).compareTo(p2.getAuthor()))
                        .toList();

        String fileName = locatieFisier + "\\" + numeFisier;

        writeInFile(fileName, entitatiFiltrateSiSortate);
    }

    private void writeInFile(String fileName, List<Document> entitati) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Document document : entitati) {
                String linie = document.toString();
                bw.write(linie);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Document document = parseDocument(line);
                if (document != null) {
                    repository.add(document);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initFromFile() {
        String fileName = locatieFisier + "\\" + numeFisier;
        readFromFile(fileName);
    }

    private Document parseDocument(String line) {
        if (line.startsWith("Manuscript {")) {
            String[] parts = line.split("[{},=\\s]+");
            String author = extractValue(line, "Author");

            int numberOfWords = Integer.parseInt(parts[5].trim());
            int numberOfPages = Integer.parseInt(parts[7].trim().replaceAll("[^0-9]", ""));

            return new Manuscript(author, numberOfWords, numberOfPages);
        } else if (line.startsWith("Presentation {")) {
            String[] parts = line.split("[{},=\\s]+");
            String author = extractValue(line, "Author");
            int numberOfSlides = Integer.parseInt(parts[5].trim());

            String text = String.join(" ", Arrays.copyOfRange(parts, 7, parts.length));

            return new Presentation(author, numberOfSlides, text);
        }

        return null;
    }

    private String extractValue(String line, String key) {
        int start = line.indexOf(key + "='") + key.length() + 2;
        int end = line.indexOf("'", start);
        return line.substring(start, end);
    }

    public void afisareNuConforme() {
        List<Document> entitati = repository.getAllEntities();

        entitati.stream().filter(p1 -> !p1.isConformant()).sorted((p1, p2) -> (p1.getAuthor()).compareTo(p2.getAuthor()))
                .forEach(flightInstrument -> System.out.println(flightInstrument));
    }
}
