// src/it/uniroma3/diadia/FabbricaDiComandi.java
package it.uniroma3.diadia.comandi;

/** Interfaccia per costruire comandi da una stringa */
public interface FabbricaDiComandi {
    /** Restituisce un comando interpretando l’istruzione testuale */
    Comando costruisciComando(String istruzione);
}
