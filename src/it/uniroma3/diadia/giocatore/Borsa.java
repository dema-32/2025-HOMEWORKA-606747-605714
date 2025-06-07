package it.uniroma3.diadia.giocatore;
import java.util.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * Classe Borsa - un giocatore ha a disposizione
 * una borsa con la quale può raccogliere attrezzi
 * sparsi lungo il labirinto
 *
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private int pesoMax;
	
	private List<Attrezzo> attrezzi;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null) return false;
		if(this.getPeso() + attrezzo.getPeso() > this.pesoMax) return false;
		
		return this.attrezzi.add(attrezzo);
	}
	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(Attrezzo a: attrezzi) {
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	public Attrezzo removeAttrezzo(Attrezzo attrezzo) { 
		Iterator<Attrezzo> it = attrezzi.iterator();
		
		while(it.hasNext()) {
			Attrezzo a = it.next();
			if(a.equals(attrezzo)) {
				it.remove();
				return a;
			}
		}
		return null;
	}
	
	public boolean isFull() {
		return this.getPeso() >= this.pesoMax;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
		
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		Set<Attrezzo> ordinati = new TreeSet<>(new Comparator<Attrezzo>() {
			
		public int compare(Attrezzo a1, Attrezzo a2) {
			int Peso = a1.getPeso() - a2.getPeso();
			if(Peso != 0) return Peso;
			return a1.getNome().compareTo(a2.getNome());
			}
		});
		ordinati.addAll(attrezzi);
		return new ArrayList<>(ordinati);
	}


	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> ordinati = new TreeSet<>(new Comparator<Attrezzo>(){
			public int compare(Attrezzo a1, Attrezzo a2) {
				if(a1.getNome().equals(a2.getNome())) return a1.getPeso() - a2.getPeso();
				
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		ordinati.addAll(attrezzi);
		return ordinati;
	}
	
}