package cabinet.repo;

import cabinet.domeniu.Entitate;
import cabinet.exceptions.DuplicateIDException;
import cabinet.exceptions.ObjectNotFoundException;
import cabinet.repo.GenericRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Clasă de test pentru GenericRepository
class GenericRepositoryTest {

    private GenericRepository<TestEntity> repository;

    // Clasă de entitate pentru testare
    static class TestEntity extends Entitate {
        public TestEntity(int id) {
            super(id);
        }
    }

    @BeforeEach
    void setUp() {
        repository = new GenericRepository<>();
    }

    @Test
    void testAddEntity() {
        TestEntity entity = new TestEntity(1);
        repository.add(entity);

        List<TestEntity> allEntities = repository.findAll();
        assertEquals(1, allEntities.size());
        assertEquals(entity, allEntities.get(0));
    }

    @Test
    void testAddDuplicateEntity() {
        TestEntity entity = new TestEntity(1);
        repository.add(entity);

        TestEntity duplicateEntity = new TestEntity(1);
        assertThrows(DuplicateIDException.class, () -> repository.add(duplicateEntity));
    }

    @Test
    void testFindEntity() {
        TestEntity entity = new TestEntity(1);
        repository.add(entity);

        TestEntity foundEntity = repository.find(1);
        assertEquals(entity, foundEntity);
    }

    @Test
    void testFindNonExistentEntity() {
        assertThrows(ObjectNotFoundException.class, () -> repository.find(99));
    }

    @Test
    void testDeleteEntity() {
        TestEntity entity = new TestEntity(1);
        repository.add(entity);

        repository.delete(1);

        List<TestEntity> allEntities = repository.findAll();
        assertTrue(allEntities.isEmpty());
    }

    @Test
    void testDeleteNonExistentEntity() {
        assertThrows(ObjectNotFoundException.class, () -> repository.delete(99));
    }

    @Test
    void testFindAllEntities() {
        TestEntity entity1 = new TestEntity(1);
        TestEntity entity2 = new TestEntity(2);

        repository.add(entity1);
        repository.add(entity2);

        List<TestEntity> allEntities = repository.findAll();
        assertEquals(2, allEntities.size());
        assertTrue(allEntities.contains(entity1));
        assertTrue(allEntities.contains(entity2));
    }
}
