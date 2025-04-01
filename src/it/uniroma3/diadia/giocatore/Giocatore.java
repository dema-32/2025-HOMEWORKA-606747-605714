package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;



/**
 * Classe Giocatore 
 * 
 * @author Alfredo e Nazar
 * @version 0.0.2.a
 */

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private int cfu;

	private Labirinto labirinto;
	private Borsa borsa;
	private IOConsole io;

	public Giocatore(Labirinto labirinto, IOConsole io) { 
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
		this.labirinto = labirinto;
		this.io = io;
	}
	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	public void vai(String direzione) {
		if(direzione==null) {
			io.mostraMessaggio("Devi scegliere una direzione!");
			direzione = io.leggiRiga();
		}
		Stanza prossimaStanza = null;
		prossimaStanza = labirinto.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			//diminuisci cfu per spostamento
			this.labirinto.setStanzaCorrente(prossimaStanza);
			int cfu = this.getCfu();
			cfu = cfu - 1;
			this.setCfu(cfu);
			//se cambi stanza ma non sei in quella vincente stampa una desc.
			if(this.labirinto.getStanzaCorrente()!=this.labirinto.getStanzaFinale()) {
				io.mostraMessaggio(labirinto.getStanzaCorrente().getDescrizione());
			}
		}

	} 

	public int getCfu() {
		return this.cfu;
	}
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public Borsa getBorsa() {
		return this.borsa;
	}
}
