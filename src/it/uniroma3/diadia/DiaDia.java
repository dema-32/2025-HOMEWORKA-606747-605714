// DiaDia.java
package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale del gioco
 */
public class DiaDia {
    static final private String MESSAGGIO_BENVENUTO =
        "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
        "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n" +
        "I locali sono popolati da strani personaggi, alcuni amici, altri... chissa!\n" +
        "Ci sono attrezzi che potrebbero servirti nell'impresa:\n" +
        "puoi raccoglierli, usarli, posarli quando ti sembrano inutili o regalarli se pensi che possano ingraziarti qualcuno.\n\n" +
        "Per conoscere le istruzioni usa il comando 'aiuto'.";

    private Partita partita;
    private IO io;

    public DiaDia(IO io) {
        this.partita = new Partita(io);
        this.io = io;
    }

    public void gioca() {
        io.mostraMessaggio(MESSAGGIO_BENVENUTO);
        String istruzione;
        do {
            istruzione = io.leggiRiga();
        } while (!processaIstruzione(istruzione));
    }

    private boolean processaIstruzione(String istruzione) {
        FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
        Comando comando = factory.costruisciComando(istruzione);
        comando.esegui(this.partita);

        if (this.partita.isVinta()) {
            io.mostraMessaggio("Hai vinto!");
        }
        return this.partita.isFinita();
    }

    public static void main(String[] args) {
        try (IOConsole io = new IOConsole()) {
            DiaDia gioco = new DiaDia(io);
            gioco.gioca();
        }
    }
}