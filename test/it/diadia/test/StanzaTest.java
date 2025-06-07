package it.diadia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {	
	private Stanza stanzaVuota, stanzaPiena, 
	stanza, stanzaDisordinata;
	
	private Attrezzo quaderno, penna, 
	orologio, libro, matita, compasso;

	@BeforeEach
	void setUp() {
		quaderno = new Attrezzo("Quaderno", 1);
		penna = new Attrezzo("Penna", 1);
		orologio = new Attrezzo("Orologio", 3);
		stanza = new Stanza("N18");
		stanza.addAttrezzo(quaderno);
		stanza.addAttrezzo(penna);
	}
	
	@BeforeEach
	void setUpStanzaVuota() {
		stanzaVuota = new Stanza("N18");
		quaderno = new Attrezzo("Quaderno", 1);
		penna = new Attrezzo("Penna", 1);
	}

	@BeforeEach
	void setUpStanzaPiena() {
		stanzaPiena = new Stanza("Stanza Piena");
		for (int i = 0; i < 10; i++) {
			stanzaPiena.addAttrezzo(new Attrezzo("Attrezzo " + i, 1));
		}
		orologio = new Attrezzo("Orologio", 3);
	}

	@BeforeEach
	void setUpStanzaDisordinata() {
		libro = new Attrezzo("Libro", 1);
		matita = new Attrezzo("Matita", 1);
		compasso = new Attrezzo("Compasso", 3);
		stanzaDisordinata = new Stanza("Un casino!");
		stanzaDisordinata.addAttrezzo(libro);
		stanzaDisordinata.addAttrezzo(null);
		stanzaDisordinata.addAttrezzo(compasso);
		stanzaDisordinata.addAttrezzo(null);
		stanzaDisordinata.addAttrezzo(null);
		stanzaDisordinata.addAttrezzo(matita);
	}
	
	// ------------- TEST addAttrezzo --------
	
	@Test
	void testAddAttrezzoSingolo() {
		assertTrue(stanza.addAttrezzo(quaderno), "Dovrebbe essere possibile aggiungere un attrezzo");
	}

	@Test
	void testAddAttrezzoOltreLimite() {
		assertFalse(stanzaPiena.addAttrezzo(orologio));
	}

	@Test
	void testAddAttrezzoNull() {
		assertFalse(stanzaPiena.addAttrezzo(null));
	}

	// ------------- TEST removeAttrezzo --------
	
	@Test
	void testRemoveStanzaConItem() {
		assertTrue(stanza.removeAttrezzo(penna));
	}

	@Test
	void testRemoveStanzaVuota() {
		assertFalse(stanzaVuota.removeAttrezzo(penna));
	}

	@Test
	void testRemoveItemNonPresente() {
		Attrezzo att = new Attrezzo("prova", 1);
		assertFalse(stanza.removeAttrezzo(att));
	}
	
	// ------------- TEST getAttrezzi --------
	
	@Test
	void testGetAttrezzi() {
	    List<Attrezzo> att = stanza.getAttrezzi();
	    
	    assertEquals(quaderno.toString(), att.get(0).toString());
	    assertEquals(penna.toString(), att.get(1).toString());
	}
	
	// ------------- TEST hasAttrezzo --------
	
	@Test
	void testHasAttrezzoConRemove() {
		assertTrue(stanza.hasAttrezzo(quaderno.getNome()),"quaderno dovrebbe esserci");
		assertTrue(stanza.removeAttrezzo(quaderno));
		assertFalse(stanza.hasAttrezzo(quaderno.getNome()),"quaderno non dovrebbe esserci più");
	}
	
	@Test
	void testHasAttrezzoNonEsistente() {
		assertFalse(stanzaVuota.hasAttrezzo(quaderno.getNome()));
	}
	
	@Test
	void testHasAttrezzoInStanzaDisordinata() {
		assertTrue(stanzaDisordinata.hasAttrezzo(compasso.getNome()));
		assertTrue(stanzaDisordinata.hasAttrezzo(libro.getNome()));
		assertTrue(stanzaDisordinata.hasAttrezzo(matita.getNome()));
	}
	
}