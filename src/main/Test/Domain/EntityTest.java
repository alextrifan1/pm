package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    Entity entity;

    @BeforeEach
    void set() {
        entity = new Entity(1);
    }
    @Test
    void setId() {
        entity.setId(2);
        assertEquals(2, entity.getId());
    }

    @Test
    void getId() {
        int id = entity.getId();
        assertEquals(1, id);
    }
}