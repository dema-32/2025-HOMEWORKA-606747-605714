package it.uniroma3.diadia;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class IOSimulator implements IO {

	private List<String> comandi;
	int i = 0;
	
	final String ANSI_RESET = "\u001B[0m";
	final String ANSI_GREEN = "\u001B[32m";

	public IOSimulator(LinkedList<String> vittoria) {
		this.comandi = vittoria;
	}


	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio + '\n');
		
	
	}
	@Override
	public String leggiRiga() {
	    if (i < comandi.size()) {
	        String s = comandi.get(i++);
	        this.mostraMessaggio(ANSI_GREEN + s + ANSI_RESET);
	        return s;
	    }
	    return null;
	}

}