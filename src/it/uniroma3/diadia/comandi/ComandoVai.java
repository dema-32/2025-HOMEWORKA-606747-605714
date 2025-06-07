package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
    private Direzione direzione;

    @Override
    public void esegui(Partita partita) {
        Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
        if (this.direzione == null) {
            System.out.println("Dove vuoi andare? Devi specificare una direzione");
            return;
        }

        Stanza prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(this.direzione);
        if (prossimaStanza == null) {
            System.out.println("Direzione inesistente");
            return;
        }

        partita.getLabirinto().setStanzaCorrente(prossimaStanza);
        System.out.println(partita.getLabirinto().getStanzaCorrente().getNome());

        // Decremento CFU del giocatore di 1
        int cfuAttuali = partita.getGiocatore().getCfu();
        partita.getGiocatore().setCfu(cfuAttuali - 1);
    }

    
    public void setParametro(String parametro) {
        try {
            this.direzione = Direzione.valueOf(parametro.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            this.direzione = null;
        }
    }

}
