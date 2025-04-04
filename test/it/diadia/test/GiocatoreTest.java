package it.diadia.test;
import it.uniroma3.diadia.giocatore.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



class GiocatoreTest {
	private Giocatore giocatore;
	
	@BeforeEach
	void setup() {
		giocatore =  new Giocatore(null, null); //stiamo inizializzando giocatore prima di ogni test
	}
	@Test
	void testDecrementaCfu() {
		fail("Not yet implemented");
	}

}
