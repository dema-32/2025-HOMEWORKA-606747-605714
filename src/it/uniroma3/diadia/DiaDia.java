package it.uniroma3.diadia;
import it.uniroma3.diadia.comandi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "prendi", "posa","borsa", "fine","aiuto"};

	private Partita partita;
	private IOConsole io;
	private FabbricaDiComandi FabbricaDiComandiFisarmonica;

	public DiaDia(IOConsole io) {
		this.partita = new Partita(io);
		this.io = io;
	}

	public void gioca() {
		String istruzione;
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);	

		do		
			istruzione = io.leggiRiga();
		while ((!processaIstruzione(istruzione)));
	}   

	
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
				comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.isVinta())

			System.out.println("Hai vinto!");
		//if (!this.partita.giocatoreIsVivo())

			//System.out.println("Hai esaurito i CFU...");

		return this.partita.isFinita();
	}

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
/*	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if(comandoDaEseguire.getNome() == null) return false;

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} 
		
		else if (comandoDaEseguire.getNome().equals("vai"))
			this.partita.getGiocatore().vai(comandoDaEseguire.getParametro());
		
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.partita.getGiocatore().posa(comandoDaEseguire.getParametro());
		
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.partita.getGiocatore().prendi(comandoDaEseguire.getParametro());
		
		else if (comandoDaEseguire.getNome().equals("borsa"))
			io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		
		else
			io.mostraMessaggio("Comando sconosciuto");

		if (this.partita.isFinita()) {
			
			if (this.partita.isVinta() && this.partita.getGiocatore().getCfu()>0) {
				io.mostraMessaggio("hai vinto con "+this.partita.getGiocatore().getCfu()+" cfu");
			}
			
			else
				io.mostraMessaggio("cfu finiti, hai perso!");
			return true;
		}
		
		return false;
	}   */

	// implementazioni dei comandi dell'utente:

/*	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(""+elencoComandi[i]);
		io.mostraMessaggio("");
	}
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");
	}*/

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}