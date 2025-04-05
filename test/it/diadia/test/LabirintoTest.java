package it.diadia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {
    private final Labirinto l = new Labirinto();
    private final Stanza sv = new Stanza("Biblioteca");
    
    
    @Test
    public void testGetStanzaVincente() {
        // Verifica che il nome della stanza vincente sia "Biblioteca"
        assertEquals(sv.getNome(), l.getStanzaVincente().getNome());
    }
    
}