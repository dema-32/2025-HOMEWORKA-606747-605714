package it.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.Test;

class StanzaBuiaTest {
	Stanza stanzaBuia;
	Attrezzo quaderno;
	
	@BeforeEach
	void setUp() {
		stanzaBuia = new StanzaBuia("stanzaBuia1", "quaderno");
	}
	
	@Test
	void testCostruttore() {
		assertNotNull(stanzaBuia);
	}
	
	@Test
	void testSenzaAttrezzo() {
		assertEquals("c'è buio pesto", stanzaBuia.getDescrizione());
	}
	
	@Test
	void testConAttrezzo() {
		quaderno = new Attrezzo("quaderno", 5);
		stanzaBuia.addAttrezzo(quaderno);
		assertNotEquals("c'è buio pesto", stanzaBuia.getDescrizione());
	}

}