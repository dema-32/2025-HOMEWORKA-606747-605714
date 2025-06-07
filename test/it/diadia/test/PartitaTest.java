package it.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	Stanza stanzaBloccata;
	Stanza stanzaEffettivamenteBloccata;
	Stanza stanza2;
	Attrezzo chiave;

	@BeforeEach
	void setUp() {
		stanzaBloccata = new StanzaBloccata("stanzaBloccata", Direzione.EST, "chiave");
		stanzaEffettivamenteBloccata = new Stanza("stanzaQualsiasi");
		stanzaBloccata.impostaStanzaAdiacente(Direzione.EST, stanzaEffettivamenteBloccata);
		stanzaBloccata.impostaStanzaAdiacente(Direzione.NORD, stanza2);
	}
	void testStampaDirezioni() {
		Set<Direzione> direzioni = stanzaBloccata.getDirezioni();
		assertTrue(direzioni.contains(Direzione.EST));
		assertTrue(direzioni.contains(Direzione.NORD));
		assertEquals(2, direzioni.size());
	}
	/*
	@Test
	void testStampaDirezioni() {
		assertEquals("est", stanzaBloccata.getDirezioni()[0]);
		assertEquals("nord", stanzaBloccata.getDirezioni()[1]);
	}*/
	@Test
	void testAccessoSenzaAttrezzo() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente(Direzione.EST));
	}
	
	@Test
	void testAccessoConAttrezzo() {
		chiave = new Attrezzo("chiave", 2);
		stanzaBloccata.addAttrezzo(chiave);
		assertEquals(stanzaEffettivamenteBloccata, stanzaBloccata.getStanzaAdiacente(Direzione.EST));
	}
}