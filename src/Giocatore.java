
public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa zaino;
	
	public int getCfu() {
		return cfu;
	}
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	public Borsa getZaino() {
		return zaino;
	}
	public void setZaino(Borsa zaino) {
		this.zaino = zaino;
	}
	public static int getCfuIniziali() {
		return CFU_INIZIALI;
	}
	
	public void menoCfu() {
	    this.cfu = this.cfu - 1;
	}

}
