package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Comando per gestire istruzioni non riconosciute.
 */
public class ComandoNonValido implements Comando {
	 private String parametro;

	    public void setParametro(String parametro) {
	        this.parametro = parametro;
	    }
    @Override
    public void esegui(Partita partita) {
        IOConsole io = new IOConsole();
        io.mostraMessaggio("Comando sconosciuto. Digita 'aiuto' per la lista dei comandi.");
    }
}
