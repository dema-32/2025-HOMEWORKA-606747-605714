package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends StanzaProtetta{
	
	private String dirBloc;
	private String chiave;
	private Partita partita;
	
	public StanzaBloccata(String nome, String dirBloc, String chiave) {
		super(nome);
		this.dirBloc = dirBloc;
		this.chiave = chiave;
		this.numeroStanzeAdiacenti = 0;
		this.setNumeroAttrezzi(0);
		this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
		this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
	    // Se la direzione è bloccata
	    if (direzione.equals(this.dirBloc)) {
	        // Controllo se ho la chiave
	        for (Attrezzo a : this.attrezzi) {
	            if (a != null && a.getNome().equals(this.chiave)) {
	                // Ho la chiave → ritorno stanza adiacente normalmente
	                return super.getStanzaAdiacente(direzione);
	            }
	        }
	        // Non ho la chiave → rimango nella stessa stanza
	        System.out.println("Direzione bloccata! Ti serve la chiave: " + this.chiave);
	        return this;
	    }

	    // Direzione diversa da quella bloccata → normale
	    return super.getStanzaAdiacente(direzione);
    }

}
