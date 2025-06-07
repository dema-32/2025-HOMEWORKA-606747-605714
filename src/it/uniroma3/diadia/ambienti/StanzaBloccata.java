package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {

    private Direzione direzioneBloccata;
    private String attrezzoSbloccante;

    public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
        super(nome);
        this.direzioneBloccata = direzioneBloccata;
        this.attrezzoSbloccante = attrezzoSbloccante;
    }

    @Override
    public Stanza getStanzaAdiacente(Direzione direzione) {
        if (direzione == null)
            return null;

        if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(attrezzoSbloccante)) {
            return this; // bloccata
        }

        return super.getStanzaAdiacente(direzione);
    }

    @Override
    public String getDescrizione() {
        String descrizione = super.getDescrizione();
        if (!this.hasAttrezzo(attrezzoSbloccante)) {
            descrizione += "\n[STANZA BLOCCATA]: la direzione '" + direzioneBloccata +
                           "' è bloccata. Serve l'attrezzo: " + attrezzoSbloccante;
        }
        return descrizione;
    }
}
