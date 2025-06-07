package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {

    private Giocatore giocatore;
    private Labirinto labirinto;
    private boolean finita;

    public Partita(IO io) {
        // Carico il labirinto dal classpath (src/main/resources/labirinto.txt)
        CaricatoreLabirinto car = new CaricatoreLabirinto();
        car.carica();
        this.labirinto = car.getLabirinto();

        // Creo il giocatore e imposto lo stato iniziale
        this.giocatore = new Giocatore(labirinto, io);
        this.finita   = false;
    }

    public Labirinto getLabirinto() {
        return this.labirinto;
    }

    public Giocatore getGiocatore() {
        return this.giocatore;
    }

    public boolean isFinita() {
        return this.finita || isVinta() || (giocatore.getCfu() == 0);
    }

    public void setFinita() {
        this.finita = true;
    }

    public boolean isVinta() {
        return this.labirinto.getStanzaCorrente() == this.labirinto.getStanzaVincente();
    }

    public Stanza getStanzaCorrente() {
        return this.labirinto.getStanzaCorrente();
    }
}
