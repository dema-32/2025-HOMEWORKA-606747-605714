package it.uniroma3.diadia;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Direzione;

public class Strega extends AbstractPersonaggio {

    public Strega(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
    public String agisci(Partita partita) {
        // Ottiene la stanza corrente in cui si trova il giocatore
        Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();

        // Inizializza una variabile che conterrà la stanza in cui la strega manderà il giocatore
        Stanza stanzaDestinazione = null;

        // Inizializza una variabile che rappresenta il numero massimo o minimo di attrezzi trovato finora
        // Se il giocatore ha salutato la strega, si cerca la stanza con più attrezzi quindi si parte da un numero molto basso
        // Se il giocatore non ha salutato la strega, si cerca la stanza con meno attrezzi quindi si parte da un numero molto alto
        int valoreAttrezzi;
        if (this.haSalutato()) {
            valoreAttrezzi = Integer.MIN_VALUE;
        } else {
            valoreAttrezzi = Integer.MAX_VALUE;
        }

        // Scorre tutte le direzioni disponibili dalla stanza corrente
        for (Direzione direzione : stanzaCorrente.getDirezioni()) {
            // Ottiene la stanza adiacente in quella direzione
            Stanza stanzaAdiacente = stanzaCorrente.getStanzaAdiacente(direzione);

            // Se la stanza esiste (non è null), allora procede con il confronto
            if (stanzaAdiacente != null) {
                int numeroAttrezzi = stanzaAdiacente.getNumeroAttrezzi();

                // Se il giocatore ha salutato, la strega è buona e cerca la stanza con più attrezzi
                if (this.haSalutato()) {
                    if (numeroAttrezzi > valoreAttrezzi) {
                        stanzaDestinazione = stanzaAdiacente;
                        valoreAttrezzi = numeroAttrezzi;
                    }
                } 
                // Altrimenti, se non ha salutato, la strega è cattiva e cerca la stanza con meno attrezzi
                else {
                    if (numeroAttrezzi < valoreAttrezzi) {
                        stanzaDestinazione = stanzaAdiacente;
                        valoreAttrezzi = numeroAttrezzi;
                    }
                }
            }
        }

        // Se è stata trovata una stanza di destinazione, allora il giocatore viene spostato lì
        if (stanzaDestinazione != null) {
            partita.getLabirinto().setStanzaCorrente(stanzaDestinazione);
            return this.getNome() + " ti ha spostato con un incantesimo nella stanza chiamata " + stanzaDestinazione.getNome();
        } 
        // Se non è stata trovata nessuna stanza adiacente valida, allora non succede nulla
        else {
            return this.getNome() + " non ha trovato una stanza dove mandarti.";
        }
    }
}