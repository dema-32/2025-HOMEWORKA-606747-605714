package it.uniroma3.diadia;

public class Cane extends AbstractPersonaggio {

    private static final String MESSAGGIO_MORSO = "GRRR! Il cane ti ha morso! Perdi 1 CFU.";

    public Cane(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
    public String agisci(Partita partita) {
        partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
        return MESSAGGIO_MORSO;
    }
}
