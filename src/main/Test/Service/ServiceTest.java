package Service;

import Domain.Piesa;
import Repository.ExceptionRepository;
import Repository.IRepository;
import Repository.MRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service<Piesa> service;
    private IRepository<Piesa> repository;

    @BeforeEach
    void setUp() {
        repository = new MRepository<>();
        service = new Service<Piesa>(repository);
    }
    @Test
    void add() throws ExceptionRepository {
        Piesa piesa = new Piesa(1, "Queen", "Bohemian Rhapsody", "Rock", "5:55");
        service.add(piesa);
        assertTrue(service.getAll().contains(piesa));
    }

    @Test
    void delete() throws ExceptionRepository {
        Piesa piesa = new Piesa(1, "Queen", "Bohemian Rhapsody", "Rock", "5:55");
        Piesa piesa2 = new Piesa(2, "Queen", "Bohemian Rhapsody", "Rock", "5:55");
        service.add(piesa);
        service.add(piesa2);
        service.delete(2);
        assertFalse(service.getAll().contains(piesa2));
        assertEquals(1, service.getAll().size());
    }

    @Test
    void update() throws ExceptionRepository {
        Piesa piesa = new Piesa(1, "Queen", "Bohemian Rhapsody", "Rock", "5:55");
        service.add(piesa);
        Piesa piesa2 = new Piesa(2, "The Weeknd", "Dawn Fm", "Pop", "3:33");
        service.update(0, piesa2);
        assertTrue(service.getAll().contains(piesa2));
    }

    @Test
    void getAll() throws ExceptionRepository {
        Piesa piesa1 = new Piesa(1, "Queen", "Bohemian Rhapsody", "Rock", "5:55");
        Piesa piesa2 = new Piesa(2, "Queen", "Bohemian Rhapsody", "Rock", "5:55");
        Piesa piesa3 = new Piesa(3, "Queen", "Bohemian Rhapsody", "Rock", "5:55");
        service.add(piesa1);
        service.add(piesa2);
        service.add(piesa3);

        assertEquals(3, service.getAll().size());
    }
}