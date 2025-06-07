package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {
	String param;
	private static final String cosa_regalo = "cosa vuoi regale?";
	
	public ComandoRegala(IO io) {
		setIO(io);
	}

	@Override
	public void esegui(Partita partita) {
		this.param = getParametro();
		if(this.param == null) getIO().mostraMessaggio(cosa_regalo);
		else {
			Attrezzo attrezzoRegalo = partita.getGiocatore().getBorsa().getAttrezzo(param);
			if(attrezzoRegalo == null) System.out.println("l'oggetto non ci sta!");
		}
		
	}

}
