package Repository;

import Domain.Piesa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SQLRepositoryTest {

    private SQLRepository repository;
    private final String testDb = "test.db";

    @BeforeEach
    void setUp() {
        repository = new SQLRepository(testDb);
    }

    @AfterEach
    void tearDown() {
        File dbFile = new File(testDb);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    @Test
    void getAll() {
        List<Piesa> piese = repository.getAll();
        assertNotNull(piese);
        assertEquals(17, piese.size());
    }

    @Test
    void add() {
        Piesa newPiesa = new Piesa(18, "New Artist", "New Song", "Pop", "3:45");
        repository.add(newPiesa);

        List<Piesa> piese = repository.getAll();
        assertEquals(18, piese.size());

        Piesa addedPiesa = piese.get(17);
        assertEquals("New Artist", addedPiesa.getFormatie());
        assertEquals("New Song", addedPiesa.getTitlu());
    }

    @Test
    void remove() {
        repository.remove(17);

        List<Piesa> piese = repository.getAll();
        assertEquals(16, piese.size());

        Piesa removedPiesa = piese.stream()
                .filter(p -> p.getId() == 17)
                .findFirst()
                .orElse(null);
        assertNull(removedPiesa);
    }
}