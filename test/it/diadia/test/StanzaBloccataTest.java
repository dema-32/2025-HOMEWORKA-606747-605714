package it.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class StanzaBloccataTest {

	@Test
    public void testStanzaBloccataBlockedWithoutKey() {
        StanzaBloccata sb = new StanzaBloccata("StanzaSegreta", "nord", "chiaveSegreta");
        // senza la chiave, non posso andare a nord, rimango nella stanza corrente
        // Simulo Partita e stanza corrente
        IO io = new IOConsole();               
        Partita partita = new Partita(io);
        sb.impostaStanzaAdiacente("nord", new Stanza("Nord"));
        // la stanza corrente di Partita è quella di sb
        assertEquals(sb, sb.getStanzaAdiacente("nord"));
  // rimango nella stessa stanza se bloccata senza chiave
    }

    @Test
    public void testStanzaBloccataUnlockedWithKey() {
        StanzaBloccata sb = new StanzaBloccata("StanzaSegreta", "nord", "chiaveSegreta");
        sb.addAttrezzo(new Attrezzo("chiaveSegreta", 1));
        Stanza nord = new Stanza("Nord");
        sb.impostaStanzaAdiacente("nord", nord);
        assertEquals(nord, sb.getStanzaAdiacente("nord"));
    }
}
