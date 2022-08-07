package banka;

import java.util.Random;

public class Plata extends Thread {

	private Racun racun;
	private int stanje=0;
	
	public Plata(Racun racun) {
		this.racun=racun;
	}
	
	@Override
	public void run() {
		try {
		while(!Thread.interrupted()) {
			
			Thread.sleep(100+ new Random().nextInt(100));
			int iznos=1 +new Random().nextInt(100);
			
			synchronized (racun) {
				racun.uvecaj(iznos);
				System.out.println("Plata u iznosu od: " + iznos 
						+"\tNovo stanje: " +racun.dohvatiStanje());
				racun.notifyAll();
			}
			
			stanje+=iznos;
			
			
		}
		} catch (InterruptedException e) {}
		System.out.println("Plata zavrsena");
	}
	
	public int dohvatiStanje() {
		return stanje;
	}
}
