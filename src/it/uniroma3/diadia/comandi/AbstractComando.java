package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

public abstract class AbstractComando implements Comando {
	private String parametro;
    private IO io;
    
    @Override
    public void setParametro(String parametro) {
    	this.parametro = parametro;
    }
    
    public String getParametro() {
    	return this.parametro;
    }
    
    public void setIO(IO io) {
    	this.io = io;
    }
    
    public IO getIO() {
    	return this.io;
    }
}