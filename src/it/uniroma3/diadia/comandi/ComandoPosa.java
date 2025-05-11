// File: ComandoPosa.java
package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Comando per posare un attrezzo dalla borsa del giocatore nella stanza corrente.
 */
public class ComandoPosa implements Comando {
    private String parametro;

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    @Override
    public void esegui(Partita partita) {
        IO io = new IOConsole();
        Giocatore giocatore = partita.getGiocatore();
        String attrezzoDaPosare = this.parametro;

        if (attrezzoDaPosare == null) {
            io.mostraMessaggio("Quale attrezzo vuoi posare?");
            attrezzoDaPosare = io.leggiRiga();
        }

        Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();

        if (giocatore.getBorsa().isEmpty()) {
            io.mostraMessaggio("La borsa è vuota");
            return;
        }

        if (!giocatore.getBorsa().hasAttrezzo(attrezzoDaPosare)) {
            io.mostraMessaggio(attrezzoDaPosare + " non presente nella borsa");
            return;
        }

        Attrezzo att = giocatore.getBorsa().removeAttrezzo(
            giocatore.getBorsa().getAttrezzo(attrezzoDaPosare)
        );
        if (att == null) {
            io.mostraMessaggio("Errore nel rimuovere l'attrezzo");
            return;
        }

        if (stanzaCorrente.getNumeroAttrezzi() >= 10) {
            io.mostraMessaggio("La stanza è piena");
            giocatore.getBorsa().addAttrezzo(att); // rollback
            return;
        }

        stanzaCorrente.addAttrezzo(att);
        io.mostraMessaggio("Hai posato " + attrezzoDaPosare + " in " + stanzaCorrente.getNome());
    }
}
