package it.uniroma3.diadia;
import java.util.Scanner;

/** 
 * La classe si occupa di gestire l'interfaccia con l'utente.
 * 
 * @author Docente di POO
 */

public class IOConsole implements IO{
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		// scannerDiLinee.close();
		return riga;
	}

}