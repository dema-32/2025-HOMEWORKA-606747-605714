package it.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	 @Test
	    public void testStanzaBuiaDarkWithoutLight() {
	        StanzaBuia sb = new StanzaBuia("Grotta", "lanterna");
	        sb.addAttrezzo(new Attrezzo("pala", 3));
	        String desc = sb.getDescrizione();
	        assertEquals("C'è buio pesto", desc);
	    }

	    @Test
	    public void testStanzaBuiaLightRevealed() {
	        StanzaBuia sb = new StanzaBuia("Grotta", "lanterna");
	        sb.addAttrezzo(new Attrezzo("lanterna", 1));
	        sb.addAttrezzo(new Attrezzo("pala", 3));
	        String desc = sb.getDescrizione();
	        assertTrue(desc.contains("Grotta"));
	        assertTrue(desc.contains("pala"));
	    }
}


