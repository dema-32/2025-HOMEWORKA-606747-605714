package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Comando per terminare la partita.
 */
public class ComandoFine implements Comando {
	 private String parametro;

	    public void setParametro(String parametro) {
	        this.parametro = parametro;
	    }
    @Override
    public void esegui(Partita partita) {
        IOConsole io = new IOConsole();
        io.mostraMessaggio("Grazie di aver giocato!");
        partita.setFinita();
    }
}