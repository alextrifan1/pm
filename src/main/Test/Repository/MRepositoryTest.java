package Repository;

import Domain.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
class MRepositoryTest {

    private MRepository<Entity> repository;
    private Entity entity1;
    private Entity entity2;

    @BeforeEach
    void setUp() {
        repository = new MRepository<>();
        entity1 = new Entity(1);
        entity2 = new Entity(2);
    }

    @Test
    void add() {
        repository.add(entity1);
        repository.add(entity2);

        assertEquals(2, repository.getAll().size());
        assertEquals(entity1, repository.findById(1));
        assertEquals(entity2, repository.findById(2));

        repository.add(entity1);
        assertEquals(2, repository.getAll().size());
    }

    @Test
    void remove() {
        repository.add(entity1);
        repository.add(entity2);

        repository.remove(1);
        assertNull(repository.findById(1));
        assertEquals(1, repository.getAll().size());

        repository.remove(3);
        assertEquals(1, repository.getAll().size());
    }

    @Test
    void update() {
        repository.add(entity1);

        Entity updatedEntity = new Entity(1);
        repository.update(0, updatedEntity);

        assertEquals(updatedEntity, repository.findById(1));
    }

    @Test
    void getAll() {
        repository.add(entity1);
        repository.add(entity2);

        Collection<Entity> allEntities = repository.getAll();
        assertEquals(2, allEntities.size());
        assertTrue(allEntities.contains(entity1));
        assertTrue(allEntities.contains(entity2));
    }

    @Test
    void findById() {
        repository.add(entity1);

        assertEquals(entity1, repository.findById(1));
        assertNull(repository.findById(3));
    }

    @Test
    void iterator() {
        repository.add(entity1);
        repository.add(entity2);

        Iterator<Entity> iterator = repository.iterator();
        assertNotNull(iterator);

        assertTrue(iterator.hasNext());
        assertEquals(entity1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(entity2, iterator.next());
        assertFalse(iterator.hasNext());
    }
}