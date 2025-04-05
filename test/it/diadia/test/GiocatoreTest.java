package it.diadia.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.IOConsole;

public class GiocatoreTest {
    private Giocatore g;
    
    @Before
    public void setUp() {
        // Creiamo un labirinto base e una istanza di IOConsole (che qui non viene usata per output)
        Labirinto lab = new Labirinto();
        IOConsole io = new IOConsole();
        g = new Giocatore(lab, io);
    }
    
    @Test
    public void testGetCfuDefault() {
        // CFU iniziali devono essere 20
        assertEquals(20, g.getCfu());
    }
    
    @Test
    public void testSetCfu() {
        g.setCfu(3);
        assertEquals(3, g.getCfu());
    }
    
    @Test
    public void testGetBorsaDefault() {
        // La borsa deve essere inizializzata (non null)
        assertNotNull(g.getBorsa());
    }
}
