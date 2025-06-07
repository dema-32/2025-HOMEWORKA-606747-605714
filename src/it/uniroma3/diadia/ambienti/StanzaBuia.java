package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.List;

public class StanzaBuia extends Stanza {
    private String attrezzoLuce;

    public StanzaBuia(String nome, String attrezzoLuce) {
        super(nome);
        this.attrezzoLuce = attrezzoLuce;
    }

    @Override
    public String getDescrizione() {
        if (this.hasAttrezzo(attrezzoLuce))
            return super.getDescrizione();
        else
            return "C'è buio pesto";
    }
}
