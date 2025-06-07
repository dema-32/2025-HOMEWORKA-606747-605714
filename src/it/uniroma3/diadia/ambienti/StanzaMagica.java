package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {
    private static final int SOGLIA_MAGICA_DEFAULT = 3;
    private int contatoreAttrezzi;
    private int sogliaMagica;

    public StanzaMagica(String nome) {
        this(nome, SOGLIA_MAGICA_DEFAULT);
    }

    public StanzaMagica(String nome, int sogliaMagica) {
        super(nome);
        this.sogliaMagica = sogliaMagica;
        this.contatoreAttrezzi = 0;
    }

    @Override
    public boolean addAttrezzo(Attrezzo attrezzo) {
        this.contatoreAttrezzi++;
        if (this.contatoreAttrezzi > this.sogliaMagica)
            attrezzo = this.modificaAttrezzo(attrezzo);
        return super.addAttrezzo(attrezzo);
    }

    private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
        String nomeInvertito = new StringBuilder(attrezzo.getNome()).reverse().toString();
        int pesoRaddoppiato = attrezzo.getPeso() * 2;
        return new Attrezzo(nomeInvertito, pesoRaddoppiato);
    }
}
