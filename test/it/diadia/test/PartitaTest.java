package it.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class PartitaTest {
    private Partita p;
    private Labirinto l;
    private Giocatore g;
    
    @Before
    public void setUp() {
       
        IOConsole io = new IOConsole();
        p = new Partita(io);
        l = p.getLabirinto();
        g = p.getGiocatore();
    }
    
    // Test per il metodo isFinita
    @Test 
    public void testPartitaNonFinitaAllInizio() {
        assertFalse(p.isFinita());
    }
    
    @Test
    public void testPartitaFinitaQuandoVinta() {
        // Impostiamo la stanza corrente come quella vincente tramite il Labirinto
        Stanza stanzaVincente = l.getStanzaVincente();
        l.setStanzaCorrente(stanzaVincente);
        assertTrue(p.isFinita());
    }
    
    @Test
    public void testPartitaFinitaQuandoZeroCfu() {
        g.setCfu(0); 
        assertTrue(p.isFinita());
    }
    
    @Test
    public void testGetStanzaCorrenteDopoSet() {
        Stanza stanza1 = new Stanza("stanza 1");
        l.setStanzaCorrente(stanza1);
        assertEquals(stanza1, l.getStanzaCorrente());

        Stanza stanza2 = new Stanza("stanza 2");
        l.setStanzaCorrente(stanza2);
        assertEquals(stanza2, l.getStanzaCorrente());

        Stanza stanza3 = new Stanza("stanza 3");
        l.setStanzaCorrente(stanza3);
        assertEquals(stanza3, l.getStanzaCorrente());
    }
    
    @Test
    public void testGetStanzaCorrenteDopoSetNull() {
        l.setStanzaCorrente(null);
        assertNull(l.getStanzaCorrente());
    }
    
    @Test
    public void testGetStanzaCorrenteRestituisceStanzaVincente() {
        l.setStanzaCorrente(l.getStanzaVincente());
        // Esempio: la stanza vincente creata nel Labirinto è "Biblioteca"
        assertEquals("Biblioteca", l.getStanzaCorrente().getNome());
    }
    
    // Test per il metodo isVinta
    @Test
    public void testVintaDopoCreazione() {
        assertFalse(p.isVinta());
    }
    
    @Test
    public void testVintaDopoSetStanzaCorrenteNonVincente() {
        Stanza stanza1 = new Stanza("stanza 1");
        l.setStanzaCorrente(stanza1);
        assertFalse(p.isVinta());
    }
    
    @Test
    public void testVintaDopoSetStanzaCorrenteVincente() {
        l.setStanzaCorrente(l.getStanzaVincente());
        assertTrue(p.isVinta());
    }
    
    // Test per il metodo getLabirinto
    @Test
    public void testGetLabirinto() {
        assertEquals(l, p.getLabirinto());
    }
    
    // Test per il metodo getGiocatore
    @Test
    public void testGetGiocatore() {
        assertEquals(g, p.getGiocatore());
    }
}
