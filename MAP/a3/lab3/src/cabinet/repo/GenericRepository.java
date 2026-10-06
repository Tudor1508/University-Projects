package cabinet.repo;

import cabinet.domeniu.Entitate;
import cabinet.exceptions.DuplicateIDException;
import cabinet.exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class GenericRepository<T extends Entitate> implements Repository<T> {
    private List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) {
        if (entities.stream().anyMatch(e -> e.getId() == entity.getId())) {
            throw new DuplicateIDException("Entitatea cu ID-ul " + entity.getId() + " există deja!");
        }
        entities.add(entity);
    }

    @Override
    public T find(int id) {
        return entities.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Entitatea cu ID-ul " + id + " nu a fost găsită!"));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(entities);
    }

    @Override
    public void delete(int id) {
        if (!entities.removeIf(e -> e.getId() == id)) {
            throw new ObjectNotFoundException("Entitatea cu ID-ul " + id + " nu a fost găsită!");
        }
    }
}
