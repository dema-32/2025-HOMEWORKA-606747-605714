package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Comando per prendere un attrezzo dalla stanza e metterlo nella borsa del giocatore.
 */
public class ComandoPrendi implements Comando {
    private String parametro;
    private IO io;

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    @Override
    public void esegui(Partita partita) {
        IOConsole io = new IOConsole();            // creo il console I/O localmente
        Giocatore giocatore = partita.getGiocatore();
        String oggettoDaPrendere = this.parametro;

        // se parametro nullo chiedo all'utente
        if (oggettoDaPrendere == null) {
            io.mostraMessaggio("Quale oggetto vuoi prendere?");
            oggettoDaPrendere = io.leggiRiga();
        }

        Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
        if (giocatore.getBorsa().isFull()) {
            io.mostraMessaggio("Borsa piena");
            return;
        }

        if (!stanzaCorrente.hasAttrezzo(oggettoDaPrendere)) {
            io.mostraMessaggio("Attrezzo non presente nella stanza");
            return;
        }

        Attrezzo oggetto = stanzaCorrente.getAttrezzo(oggettoDaPrendere);
        boolean aggiunto = giocatore.getBorsa().addAttrezzo(oggetto);
        if (aggiunto) {
            stanzaCorrente.removeAttrezzo(oggetto);
            io.mostraMessaggio("Hai aggiunto " + oggettoDaPrendere + " nella tua borsa");
        } else {
            io.mostraMessaggio("Impossibile aggiungere l'attrezzo");
        }
    }
}