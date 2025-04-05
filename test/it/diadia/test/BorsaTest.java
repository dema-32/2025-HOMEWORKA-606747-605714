package it.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
    private Borsa b;
    private final Attrezzo a = new Attrezzo("prova", 2);
    private final Attrezzo ascia = new Attrezzo("ascia", 5);
    private final Attrezzo spadone = new Attrezzo("spadone", 12);
    
    @Before
    public void setUp() {
        b = new Borsa();
    }
    
    @Test
    public void testAddAttrezzo() {
        assertTrue(b.addAttrezzo(a));
    }
    
    @Test
    public void testAddAttrezzoNull() {
        // Se si passa null, l'aggiunta deve restituire false
        assertFalse(b.addAttrezzo(null));
    }
    
    @Test
    public void testAddAttrezzoDiversoNull() {
        // L'aggiunta di un attrezzo valido non deve restituire null
        assertNotNull(b.addAttrezzo(a));
    }
    
    @Test
    public void testAddAttrezzoPesoMinoreDiDieci() {
        // Un attrezzo con peso inferiore al limite (peso max=10) deve essere aggiunto
        assertTrue(b.addAttrezzo(ascia));
    }
    
    @Test
    public void testAddAttrezzoPesoMaggioreDiDieci() {
        // Un attrezzo troppo pesante non deve essere aggiunto
        assertFalse(b.addAttrezzo(spadone));
    }
}
