package it.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Labirinto;


class IOSimulatorTest {
	
	IO io;
	DiaDia gioco;
	/*
	@Test
	void testVittoria() {
		io = new IOSimulator(new LinkedList<>(List.of(
			    "vai ovest", "vai ovest", "prendi chiave", "vai ovest", 
			    "prendi lanterna", "vai est", "vai nord", "posa lanterna", "guarda",
			    "vai nord", "posa chiave", "vai nord", "vai nord"
			)));


		Labirinto labirinto =  Labirinto.newBuilder()
			.addStanzaIniziale("Atrio").addAttrezzo("osso", "d", 1)
			.addStanza("Laboratorio Campus").addAdiacenza("Atrio", "Laboratorio Campus", Direzione.NORD)
			.addStanza("Aula N11").addAttrezzo("chiave", 1).addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
			.addStanza("Aula N10").addAttrezzo("lanterna", 3).addAdiacenza("Aula N11", "Aula N10", "ovest")
			.addAdiacenza("Aula N10", "Aula N11", "est")
			.addStanza("N2").addAdiacenza("Aula N11", "N2", "nord")
			.addAdiacenza("N2", "Aula N11", "sud")
			.addStanza("Bagno").addAdiacenza("N2", "Bagno", "nord")
			.addAdiacenza("Bagno", "N2", "sud")
			.addStanza("N9").addAdiacenza("Bagno", "N9", "nord")
			.addAdiacenza("N9", "Bagno", "sud")
			.addStanza("N3").addAdiacenza("N9", "N3", "ovest")
			.addStanzaVincente("Biblioteca").addAdiacenza("N9", "Biblioteca", "nord")
			.addAdiacenza("Biblioteca", "N9", "sud")
			.getLabirinto();
		
		gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		
		assertFalse(gioco.getPartita().isVinta());
	}
*/

}