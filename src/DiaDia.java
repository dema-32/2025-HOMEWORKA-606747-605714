import java.util.Scanner;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	

	public DiaDia() {
		this.partita = new Partita();
		this.labirinto = new Labirinto();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if (partita.isFinita()) {
			System.out.println("Hai finito i cfu, sarà per la prossima.");
			this.fine(); 
			return true;
		}
		
		if (comandoDaEseguire.getNome() == null) { 
	        System.out.println("Nessun comando inserito.");
	        return false;
		}
		
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
			
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		
		//else if (comandoDaEseguire.getNome().equals("posa"))
		//	this.posa(comandoDaEseguire.getParametro());
		
		else {
			System.out.println("Comando sconosciuto");
			return false;
		}
		
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		
		if (direzione == null) {
		    System.out.println("Devi scegliere una direzione!");
		    return;
		}
		
		Labirinto labirinto = partita.getLabirinto();
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		
		else {
			labirinto.setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().menoCfu(); //DA CAMBIARE!!!!!!!
		}
		System.out.println(labirinto.getStanzaCorrente().getDescrizione()); //descrizione? problema in attrezzo ultime righe
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}
	
	
	private void prendi(String oggetto) {
		
		if (oggetto == null) {
		    System.out.println("Devi dirmi che oggetto vuoi prendere!");
		    return;
		}
		
		Attrezzo attrezzodaprendere = partita.getLabirinto().getStanzaCorrente().getAttrezzo(oggetto);
		partita.getLabirinto().getStanzaCorrente().removeAttrezzo(oggetto);
		partita.getGiocatore().getZaino().addAttrezzo(attrezzodaprendere);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}


//ore passate: 3.5 + 1.5 + 3 + 
//ho modificato nella classe stanza il metodo tostring alla riga 118