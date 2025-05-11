package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends StanzaProtetta{
	
	private String attrezzoLuce;
	
	public StanzaBuia(String nome, String attrezzoLuce) {
		super(nome);
		this.attrezzoLuce = attrezzoLuce;
		this.numeroStanzeAdiacenti = 0;
		this.setNumeroAttrezzi(0);
		this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
		this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
	}
	
	@Override
	public String getDescrizione() {
		return this.toString();
	}
	
	public String toString() {
		
		int vero = 0;
		
		for (int i = 0; i < attrezzi.length; i++) {
			
			if (attrezzi[i]!= null && attrezzi[i].getNome().equals(attrezzoLuce))
				vero = 1;
		}
		
		if (vero == 1) {
			StringBuilder risultato = new StringBuilder();
			risultato.append(this.nome);
			risultato.append("\nUscite: ");
			
			for (String direzione : this.direzioni)
				if (direzione!=null)
					risultato.append(" " + direzione);
			
				risultato.append("\nAttrezzi nella stanza: ");
				
			for (Attrezzo attrezzo : this.attrezzi) {
				if(attrezzo != null)
					risultato.append(attrezzo.toString()+" ");
		}
			
			return risultato.toString();
		}
		
		else
			return ("C'è buio pesto");
	}
}
