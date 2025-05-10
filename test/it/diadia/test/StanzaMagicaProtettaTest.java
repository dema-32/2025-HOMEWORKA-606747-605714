package it.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaMagicaProtetta;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaProtettaTest {
	
	@Test
    public void testStanzaMagicaDefaultThreshold() {
        StanzaMagicaProtetta sm = new StanzaMagicaProtetta("SalaMagica");
        sm.addAttrezzo(new Attrezzo("a", 1));
        sm.addAttrezzo(new Attrezzo("b", 2));
        sm.addAttrezzo(new Attrezzo("c", 3));
        // quarto attrezzo attiva magia: nome invertito e peso raddoppiato
        Attrezzo d = new Attrezzo("d", 4);
        sm.addAttrezzo(d);
        Attrezzo sf = sm.getAttrezzo("d");
        assertNotNull(sf);
        assertEquals(8, sf.getPeso());
    }
}
