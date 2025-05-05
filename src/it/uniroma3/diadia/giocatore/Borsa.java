package it.uniroma3.diadia.giocatore;

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
	
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.attrezzi.length; i++)
 			if (this.attrezzi[i] != null && this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.attrezzi.length; i++) {
			if(this.attrezzi[i] != null)
				peso += this.attrezzi[i].getPeso();
		}
		return peso;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	public Attrezzo removeAttrezzo(Attrezzo attrezzo) { 
		if (attrezzo == null) {
			return attrezzo;
		}

		for (int i = 0; i < this.attrezzi.length; i++) {
			if (this.attrezzi[i] != null && this.attrezzi[i].toString().equals(attrezzo.toString())){
				this.attrezzi[i] = null; 
				this.numeroAttrezzi -= 1;
				return attrezzo; 
			} 
		}

		return attrezzo; 
	}
	
	public boolean isFull() {
		return this.numeroAttrezzi == 10;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}


}