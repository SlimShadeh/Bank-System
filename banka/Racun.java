package banka;

public class Racun {

	private int stanje;
	
	public Racun(int pocetniIznos) {
		this.stanje=pocetniIznos;
	}
	
	public void uvecaj(int iznos) {
		this.stanje+=iznos;
	}
	public void umanji(int iznos) {
		this.stanje-=iznos;
	}
	public int dohvatiStanje() {
		return stanje;
	}
}
