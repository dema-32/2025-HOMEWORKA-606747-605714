package it.uniroma3.diadia.ambienti;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.AbstractPersonaggio;
import it.uniroma3.diadia.Cane;
import it.uniroma3.diadia.Mago;
import it.uniroma3.diadia.Strega;

public class CaricatoreLabirinto {

    private static final String RESOURCE_PATH = "/labirinto.txt";
    private static final String STANZE_MARKER = "Stanze:";
    private static final String STANZE_MAGICHE_MARKER = "Magica:";
    private static final String STANZE_BUIE_MARKER = "Buia:";
    private static final String STANZE_BLOCCATE_MARKER = "Bloccata:";
    private static final String STANZA_INIZIALE_MARKER = "Inizio:";
    private static final String STANZA_VINCENTE_MARKER = "Vincente:";
    private static final String PERSONAGGI_MARKER_MAGO = "Mago:";
    private static final String PERSONAGGI_MARKER_STREGA = "Strega:";
    private static final String PERSONAGGI_MARKER_CANE = "Cane:";
    private static final String ATTREZZI_MARKER = "Attrezzi:";
    private static final String USCITE_MARKER = "Uscite:";

    private LineNumberReader reader;
    private Map<String, Stanza> nome2stanza = new HashMap<>();
    private Stanza stanzaIniziale;
    private Stanza stanzaVincente;

    public CaricatoreLabirinto() {
        InputStream in = getClass().getResourceAsStream(RESOURCE_PATH);
        if (in == null) {
            throw new RuntimeException("Risorsa non trovata: " + RESOURCE_PATH);
        }
        this.reader = new LineNumberReader(new InputStreamReader(in, StandardCharsets.UTF_8));
    }

    public void carica() {
        try {
            leggiECreaStanze();
            leggiECreaStanzeMagiche();
            leggiECreaStanzeBuie();
            leggiECreaStanzeBloccate();
            leggiInizialeEvincente();
            leggiECreaMaghi();
            leggiECreaStreghe();
            leggiECreaCani();
            leggiECollocaAttrezzi();
            leggiEImpostaUscite();
        } catch (FormatoFileNonValidoException e) {
            throw new RuntimeException("Errore parsing labirinto: " + e.getMessage(), e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String leggiRigaCheCominciaPer(String marker) {
        try {
            String riga = reader.readLine();
            if (riga == null || !riga.startsWith(marker)) {
                throw new FormatoFileNonValidoException("Linea attesa: " + marker);
            }
            return riga.substring(marker.length()).trim();
        } catch (IOException e) {
            throw new FormatoFileNonValidoException(e.getMessage(), e);
        }
    }

    private void leggiECreaStanze() {
        String nomi = leggiRigaCheCominciaPer(STANZE_MARKER);
        for (String nome : separa(nomi)) {
            nome2stanza.put(nome, new Stanza(nome));
        }
    }

    private void leggiECreaStanzeMagiche() {
        String nomi = leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
        for (String nome : separa(nomi)) {
            nome2stanza.put(nome, new StanzaMagica(nome));
        }
    }

        private void leggiECreaStanzeBuie() {
        String specs = leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
        for (String spec : separa(specs)) {
            try (Scanner sc = new Scanner(spec)) {
                String nome = sc.next();
                String attrezzo = sc.next();
                nome2stanza.put(nome, new StanzaBuia(nome, attrezzo));
            }
        }
    }
    

        private void leggiECreaStanzeBloccate() {
            String specs = leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
            for (String spec : separa(specs)) try (Scanner sc = new Scanner(spec)) {
                String nome = sc.next();                         // "Magazzino"
                String direzioneStr = sc.next();                 // "EST"
                String attrezzoSbloccante = sc.next();           // "chiave"
                Direzione direzione = Direzione.valueOf(direzioneStr.toUpperCase());

                nome2stanza.put(nome, new StanzaBloccata(nome, direzione, attrezzoSbloccante));
            }
        }


    private void leggiInizialeEvincente() {
        String ini = leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
        String vin = leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
        if (!nome2stanza.containsKey(ini) || !nome2stanza.containsKey(vin)) {
            throw new FormatoFileNonValidoException("Stanze iniziale o vincente non definite");
        }
        stanzaIniziale = nome2stanza.get(ini);
        stanzaVincente = nome2stanza.get(vin);
    }

    private void leggiECreaMaghi() {
        String specs = leggiRigaCheCominciaPer(PERSONAGGI_MARKER_MAGO);
        for (String spec : separa(specs)) try (Scanner sc = new Scanner(spec)) {
            nome2stanza.get(sc.next()).setPersonaggio(new Mago(sc.next(), sc.next(), new Attrezzo(sc.next(), 4)));
        }
    }

    private void leggiECreaStreghe() {
        String specs = leggiRigaCheCominciaPer(PERSONAGGI_MARKER_STREGA);
        for (String spec : separa(specs)) try (Scanner sc = new Scanner(spec)) {
            nome2stanza.get(sc.next()).setPersonaggio(new Strega(sc.next(), sc.next()));
        }
    }

    private void leggiECreaCani() {
        String specs = leggiRigaCheCominciaPer(PERSONAGGI_MARKER_CANE);
        for (String spec : separa(specs)) try (Scanner sc = new Scanner(spec)) {
            nome2stanza.get(sc.next()).setPersonaggio(new Cane(sc.next(), sc.next()));
        }
    }

    private void leggiECollocaAttrezzi() {
        String specs = leggiRigaCheCominciaPer(ATTREZZI_MARKER);
        for (String spec : separa(specs)) try (Scanner sc = new Scanner(spec)) {
            String nomeAttrezzo = sc.next();
            int peso = sc.nextInt();
            String nomeStanza = sc.next();

            Stanza stanza = nome2stanza.get(nomeStanza);
            if (stanza != null) {
                stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
            } else {
                System.err.println("Stanza non trovata: " + nomeStanza);
            }
        }
    }



    private void leggiEImpostaUscite() {
        String specs = leggiRigaCheCominciaPer(USCITE_MARKER);
        for (String spec : separa(specs)) try (Scanner sc = new Scanner(spec)) {
            nome2stanza.get(sc.next()).impostaStanzaAdiacente(Direzione.valueOf(sc.next()), nome2stanza.get(sc.next()));
        }
    }

    private List<String> separa(String s) {
        List<String> list = new LinkedList<>();
        try (Scanner sc = new Scanner(s)) {
            sc.useDelimiter(",");
            while (sc.hasNext()) list.add(sc.next().trim());
        }
        return list;
    }

    public Labirinto getLabirinto() {
        Labirinto lab = new Labirinto();
        lab.setStanzaIniziale(stanzaIniziale);
        lab.setStanzaVincente(stanzaVincente);
        lab.setStanzaCorrente(stanzaIniziale);
        return lab;
    }

    public Stanza getStanzaIniziale() {
        return stanzaIniziale;
    }

    public Stanza getStanzaVincente() {
        return stanzaVincente;
    }

    public static class FormatoFileNonValidoException extends RuntimeException {
        public FormatoFileNonValidoException(String msg) {
            super(msg);
        }
        public FormatoFileNonValidoException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }
}
