package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.AbstractPersonaggio;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;

public class ComandoInteragisci implements Comando {
    private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
    private String messaggio;
    private IO io;

    @Override
    public void esegui(Partita partita) {
        AbstractPersonaggio personaggio;
        personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();

        if (personaggio != null) {
            this.messaggio = personaggio.agisci(partita);
            io.mostraMessaggio(this.messaggio);
        } else {
            io.mostraMessaggio(MESSAGGIO_CON_CHI);
        }
    }

    public String getMessaggio() {
        return this.messaggio;
    }

    @Override
    public void setParametro(String parametro) {
        // vuoto: comando non ha parametri
    }
}