// File: ComandoGuarda.java
package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Comando per mostrare le informazioni della stanza corrente.
 */
public class ComandoGuarda implements Comando {
	 private String parametro;

	    public void setParametro(String parametro) {
	        this.parametro = parametro;
	    }
    @Override
    public void esegui(Partita partita) {
        IOConsole io = new IOConsole();
        String descrizioneStanza = partita.getLabirinto().getStanzaCorrente().toString();
        io.mostraMessaggio(descrizioneStanza);
    }
}
