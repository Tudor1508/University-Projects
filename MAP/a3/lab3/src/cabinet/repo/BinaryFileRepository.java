package cabinet.repo;

import cabinet.domeniu.Entitate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileRepository<T extends Entitate> implements Repository<T> {
    private String filePath;

    public BinaryFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void add(T entity) {
        List<T> entities = findAll();
        if (entities.stream().anyMatch(e -> e.getId() == entity.getId())) {
            throw new RuntimeException("Entitatea există deja cu ID-ul: " + entity.getId());
        }
        entities.add(entity);
        writeToFile(entities);
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
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject(); // Citește lista serializată din fișier
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Eroare la citirea fișierului binar: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        List<T> entities = findAll();
        if (!entities.removeIf(e -> e.getId() == id)) {
            throw new RuntimeException("Entitatea cu ID-ul " + id + " nu a fost găsită!");
        }
        writeToFile(entities);
    }

    private void writeToFile(List<T> entities) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(entities);
        } catch (IOException e) {
            throw new RuntimeException("Eroare la scrierea în fișierul binar: " + e.getMessage());
        }
    }

    public void resetFile() {
        writeToFile(new ArrayList<>());
    }
}
