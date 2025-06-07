package it.uniroma3.diadia.ambienti;
import java.util.*;

import it.uniroma3.diadia.AbstractPersonaggio;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;

	private List<Attrezzo> attrezzi;
	private Map<Direzione, Stanza> direzione2stanzaAdiacente;
	private Map<String,Stanza> stanzeAdiacenti;

	private it.uniroma3.diadia.AbstractPersonaggio personaggio;
	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new ArrayList<>();
		this.direzione2stanzaAdiacente = new HashMap<>();
	}


	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */

	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if(this.direzione2stanzaAdiacente.size() >= NUMERO_MASSIMO_DIREZIONI)
			return;
		this.direzione2stanzaAdiacente.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.direzione2stanzaAdiacente.get(direzione);
	}


	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	public boolean isFull() {
		if(this.getNumeroAttrezzi() == NUMERO_MASSIMO_ATTREZZI) return true;
		return false;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getNumeroAttrezzi() < NUMERO_MASSIMO_ATTREZZI) {
			return attrezzi.add(attrezzo);
		}
		else {
			return false;
		}
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.direzione2stanzaAdiacente.keySet().toString());
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo != null)
				risultato.append(attrezzo.toString()+" ");
		}
		if(this.getPersonaggio()!=null) {
			risultato.append("\nPersonaggo:" + this.getPersonaggio().toString());
		}
		return risultato.toString();
	}
	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo != null) {
				if (attrezzo.getNome().equals(nomeAttrezzo))
					trovato = true;
			}
		}
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo != null)
				if (attrezzo.getNome().equals(nomeAttrezzo))
					attrezzoCercato = attrezzo;
		}
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param attezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null) return false;
		for(Attrezzo a: attrezzi) {
			if(attrezzo.getNome().equals(a.getNome()))
				return attrezzi.remove(a);
		}
		return false;			//non trovo l'attrezzo nella stanza

	}


	public Set<Direzione> getDirezioni(){
		return this.direzione2stanzaAdiacente.keySet();
	}

	public int getNumeroAttrezzi() {
		return attrezzi.size();
	}


}