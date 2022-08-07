package banka;

import java.util.Random;

public class Bankomat extends Thread {

	private Racun racun;
	private int stanje=0;
	
	public Bankomat(Racun racun) {
		this.racun=racun;
	}
	
	@Override
	public void run() {
		try {
		while(!Thread.interrupted()) {
			
			Thread.sleep(100+ new Random().nextInt(100));
			 
			int iznos=1 +new Random().nextInt(1000);
			
			synchronized (racun) {
				
				System.out.println("Pokusaj podizanja: " + iznos 
			+"\tStanje: " + racun.dohvatiStanje());
				
				while(iznos>racun.dohvatiStanje()) {
					racun.wait();
				}
				racun.umanji(iznos);
				System.out.println("Podignuto na bankomatu: " + iznos + 
						"\tNovo stanje: " + racun.dohvatiStanje());
			}
			stanje-=iznos;
			
			
			}
		}
		catch (InterruptedException e) {}
		System.out.println("Bankomat zavrsio");
	}
	
	public int dohvatiStanje() {
		return stanje;
	}
}
