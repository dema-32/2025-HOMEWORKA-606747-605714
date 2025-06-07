package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Gestisce l'interfaccia con l'utente usando try-with-resources
 */
public class IOConsole implements IO, AutoCloseable {
    private Scanner scanner;

    public IOConsole() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    @Override
    public String leggiRiga() {
        return this.scanner.nextLine();
    }

    @Override
    public void close() {
        if (this.scanner != null) {
            this.scanner.close();
        }
    }
}
