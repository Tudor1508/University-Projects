package cabinet.repo;

import cabinet.domeniu.Entitate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TextFileRepository<T extends Entitate> implements Repository<T> {
    private final String filePath;
    private final Function<String, T> mapper;
    private final Function<T, String> formatter;

    public TextFileRepository(String filePath, Function<String, T> mapper, Function<T, String> formatter) {
        this.filePath = filePath;
        this.mapper = mapper;
        this.formatter = formatter;
    }

    @Override
    public void add(T entity) {
        List<T> entities = findAll(); // Citește entitățile existente
        if (entities.stream().anyMatch(e -> e.getId() == entity.getId())) {
            throw new RuntimeException("Entitatea există deja cu ID-ul: " + entity.getId());
        }
        entities.add(entity); // Adaugă noua entitate

        // Scrie lista completă în fișier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T e : entities) {
                writer.write(formatter.apply(e));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Eroare la scrierea în fișier: " + e.getMessage());
        }
    }

    @Override
    public T find(int id) {
        return findAll().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Entitatea cu ID-ul " + id + " nu a fost găsită!"));
    }

    @Override
    public List<T> findAll() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<T> entities = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                entities.add(mapper.apply(line));
            }
            return entities;
        } catch (IOException e) {
            throw new RuntimeException("Eroare la citirea fișierului!");
        }
    }

    @Override
    public void delete(int id) {
        List<T> entities = findAll();
        if (!entities.removeIf(e -> e.getId() == id)) {
            throw new RuntimeException("Entitatea cu ID-ul " + id + " nu a fost găsită!");
        }

        // Suprascrie fișierul cu entitățile rămase
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T e : entities) {
                writer.write(formatter.apply(e));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Eroare la scrierea în fișier: " + e.getMessage());
        }
    }
}
