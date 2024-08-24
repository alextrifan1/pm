package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PiesaFactoryTest {

    private PiesaFactory piesaFactory;

    @BeforeEach
    void setUp() {
        piesaFactory = new PiesaFactory();
    }

    @Test
    void createEntity() {
        String inputLine = "1,Queen,Bohemian Rhapsody,Rock,5:55";
        Piesa piesa = piesaFactory.createEntity(inputLine);

        assertEquals(1, piesa.getId());
        assertEquals("Queen", piesa.getFormatie());
        assertEquals("Bohemian Rhapsody", piesa.getTitlu());
        assertEquals("Rock", piesa.getGenMuzical());
        assertEquals("5:55", piesa.getDurata());
    }

    @Test
    void saveEntity() {
        Piesa piesa = new Piesa(1, "Queen", "Bohemian Rhapsody", "Rock", "5:55");
        String outputLine = piesaFactory.saveEntity(piesa);
        String expectedOutput = "1,Queen,Bohemian Rhapsody,Rock,5:55\n";

        assertEquals(expectedOutput, outputLine);
    }
}