package cabinet.repo;

import java.util.List;

public interface Repository<T> {
    void add(T entity);
    T find(int id);
    List<T> findAll();
    void delete(int id);
}
