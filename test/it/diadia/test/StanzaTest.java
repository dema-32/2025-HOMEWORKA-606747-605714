package it.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
    private Stanza stanza;  
    private Attrezzo attrezzo1;
    private Attrezzo attrezzo2;
    private Attrezzo attrezzo3;
    
    @Before
    public void setUp() {
        stanza = new Stanza("stanza"); 
        attrezzo1 = new Attrezzo("attrezzo1", 1);
        attrezzo2 = new Attrezzo("attrezzo2", 2);
        attrezzo3 = new Attrezzo("attrezzo3", 3);
    }
    
    // Test per il metodo addAttrezzo
    @Test
    public void testAddAttrezzo() {
        assertTrue(stanza.addAttrezzo(attrezzo1));
    }
    
    @Test
    public void testAddAttrezzoStanzaPiena() {
        for(int i = 0; i < 10; i++) {
            stanza.addAttrezzo(new Attrezzo("attrezzo" + i, i));
        }
        assertFalse(stanza.addAttrezzo(attrezzo3));
    }
    
    @Test
    public void testAddAttrezzoNull() {
        assertFalse(stanza.addAttrezzo(null));
    }
    
    // Test per il metodo hasAttrezzo
    @Test
    public void testHasAttrezzo() {
        stanza.addAttrezzo(attrezzo2);
        assertTrue(stanza.hasAttrezzo("attrezzo2"));
    }
    
    @Test
    public void testHasAttrezzoNonEsistente() {
        assertFalse(stanza.hasAttrezzo("attrezzoNonEsistente"));
    }
    
    @Test
    public void testHasAttrezzoNull() {
        assertFalse(stanza.hasAttrezzo(null));
    }
    
    // Test per il metodo getAttrezzo
    @Test
    public void testGetAttrezzoStanzaVuota() {
        assertNull(new Stanza("stanza").getAttrezzo("attrezzo"));
    }
    
    @Test
    public void testGetAttrezzoStanzaAttrezzoPresente() {
        Stanza s = new Stanza("stanza");
        s.addAttrezzo(new Attrezzo("attrezzo", 1));
        assertNotNull(s.getAttrezzo("attrezzo"));
    }
    
    @Test
    public void testGetAttrezzoStanzaAttrezzoNonPresente() {
        Stanza s = new Stanza("stanza");
        s.addAttrezzo(new Attrezzo("attrezzo", 1));
        assertNull(s.getAttrezzo("attrezzoNonPresente"));
    }
    
    // Test per il metodo getStanzaAdiacente
    @Test
    public void testGetStanzaAdiacente_StanzaEsistente() {
        Stanza s1 = new Stanza("stanza1");
        Stanza s2 = new Stanza("stanza2");
        s1.impostaStanzaAdiacente("nord", s2);
        assertEquals(s2, s1.getStanzaAdiacente("nord"));
    }
    
    @Test
    public void testGetStanzaAdiacente_StanzaNonEsistente() {
        Stanza s = new Stanza("stanza");
        assertNull(s.getStanzaAdiacente("nord"));
    }
    
    @Test
    public void testGetStanzaAdiacenteDirezioneNull() {
        Stanza s = new Stanza("stanza");
        assertNull(s.getStanzaAdiacente(null));
    }
}
