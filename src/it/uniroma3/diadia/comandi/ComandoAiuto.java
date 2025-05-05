package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Comando per mostrare la lista dei comandi disponibili.
 */
public class ComandoAiuto implements Comando {
	private IO IOConsole;
	
    private static final String[] ELENCO_COMANDI = {"vai", "prendi", "posa", "guarda", "aiuto", "fine"};
    private String parametro;

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    @Override
    public void esegui(Partita partita) {
    	IO IOConsole = io;
        // Stampo i comandi definiti localmente
        for (int i = 0; i < ELENCO_COMANDI.length; i++) {
            io.mostraMessaggio(ELENCO_COMANDI[i]);
        }
        io.mostraMessaggio(""); // riga vuota finale
    }
}