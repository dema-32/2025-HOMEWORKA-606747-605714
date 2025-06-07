package it.uniroma3.diadia.giocatore;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private int pesoMax;

	private List<Attrezzo> attrezzi;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null) return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
	
		return this.attrezzi.add(attrezzo);
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(Attrezzo a: attrezzi) {
			if(a.getNome().equals(nomeAttrezzo)) {
				return a;
			}
		}
		return null;
	}
	
	public int getPeso() {
		int peso = 0;
		for(Attrezzo a: attrezzi) {
			peso += a.getPeso();
		}
		return peso;
	}
	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}
	public boolean isFull() {
		return this.getPeso() >= this.pesoMax;
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
	
	
	
	
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		Iterator<Attrezzo> it = attrezzi.iterator();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			
			while(it.hasNext()) {
				s.append(it.next().toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		Set<Attrezzo> ordinati = new TreeSet<>(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				int diffPeso = a1.getPeso() - a2.getPeso();
				if(diffPeso != 0) return diffPeso;
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		ordinati.addAll(attrezzi);
		return new ArrayList<>(ordinati);
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> ordinati = new TreeSet<>(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if(a1.getNome().equals(a2.getNome())) return a1.getPeso() - a2.getPeso();
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		ordinati.addAll(attrezzi);
		return ordinati;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> map = new HashMap<>();
		
		for(Attrezzo a: attrezzi) {
			if(!map.containsKey(a.getPeso())) {
				map.put(a.getPeso(), new HashSet<Attrezzo>());
			}
			map.get(a.getPeso()).add(a);
		}
		
		return map;
	}
}