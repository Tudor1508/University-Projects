package cabinet.repo;

import cabinet.domeniu.Entitate;

import java.util.List;

public interface Repository<T extends Entitate> {
    void add(T entity);
    T find(int id);
    List<T> findAll();
    void delete(int id);
}
