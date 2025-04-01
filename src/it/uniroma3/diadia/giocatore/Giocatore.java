package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;



/**
 * Classe Giocatore 
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
	
	public boolean prendi(String oggettoDaPrendere) {
		if(oggettoDaPrendere == null) {
			io.mostraMessaggio("quale oggetto vuoi prendere?");
			oggettoDaPrendere = io.leggiRiga();
		}
		if(!borsa.Full()) {
			Stanza stanzaCorrente = labirinto.getStanzaCorrente();
			if(stanzaCorrente.hasAttrezzo(oggettoDaPrendere)) {
				Attrezzo oggetto = stanzaCorrente.getAttrezzo(oggettoDaPrendere);
				borsa.addAttrezzo(oggetto);
				stanzaCorrente.removeAttrezzo(stanzaCorrente.getAttrezzo(oggettoDaPrendere));
				io.mostraMessaggio("hai aggiunto" + oggettoDaPrendere + "nella tua borsa");
				return true;
			}
		}
		else {
			io.mostraMessaggio("borsa piena");
		}
		return false;
	}
	
	public boolean posa(String attrezzoDaPosare) {
		if(attrezzoDaPosare == null) {
			io.mostraMessaggio("Quale attrezzo vuoi posare?");
			attrezzoDaPosare = io.leggiRiga();
		}
		Stanza stanzaCorrente = labirinto.getStanzaCorrente();
		if(!borsa.isEmpty()) {
			if(borsa.hasAttrezzo(attrezzoDaPosare)) {
				if(stanzaCorrente.getNumeroAttrezzi() < 10) {
					Attrezzo att = borsa.removeAttrezzo(borsa.getAttrezzo(attrezzoDaPosare));
					if(att != null) {
						stanzaCorrente.addAttrezzo(att);
						io.mostraMessaggio("Hai posato "+ attrezzoDaPosare +" in "+ stanzaCorrente.getNome());
						return true;
					}
				}else io.mostraMessaggio("La stanza è piena");
			}else io.mostraMessaggio(attrezzoDaPosare + " non presente nella borsa");
		}else io.mostraMessaggio("La borsa è vuota");
		return false;
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
