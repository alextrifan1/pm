package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PiesaTest {

    private Piesa piesa;

    @BeforeEach
    void setUp() {
        piesa = new Piesa(1, "Queen", "Bohemian Rhapsody", "Rock", "5:55");
    }

    @Test
    void getFormatie() {
        assertEquals("Queen", piesa.getFormatie());
    }

    @Test
    void getTitlu() {
        assertEquals("Bohemian Rhapsody", piesa.getTitlu());
    }

    @Test
    void getGenMuzical() {
        assertEquals("Rock", piesa.getGenMuzical());
    }

    @Test
    void getDurata() {
        assertEquals("5:55", piesa.getDurata());
    }

    @Test
    void setFormatie() {
        piesa.setFormatie("The Beatles");
        assertEquals("The Beatles", piesa.getFormatie());
    }

    @Test
    void setTitlu() {
        piesa.setTitlu("Hey Jude");
        assertEquals("Hey Jude", piesa.getTitlu());
    }

    @Test
    void setGenMuzical() {
        piesa.setGenMuzical("Pop");
        assertEquals("Pop", piesa.getGenMuzical());
    }

    @Test
    void setDurata() {
        piesa.setDurata("7:08");
        assertEquals("7:08", piesa.getDurata());
    }

    @Test
    void testToString() {
        String expected = " id: 1 formatie: Queen titlu: Bohemian Rhapsody gen_muzical: Rock durata: 5:55";
        assertEquals(expected, piesa.toString());
    }
}