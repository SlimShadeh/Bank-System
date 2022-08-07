package skladiste;

import java.util.Random;

public class Proizvodjac extends Thread {

	private Skladiste skladiste;
	private int id;
	
	public Proizvodjac(Skladiste skladiste,int id) {
		this.skladiste=skladiste;
		this.id=id;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
			
				Thread.sleep(100+ new Random().nextInt(1000));
				
				int elem=new Random().nextInt(10);
				synchronized (skladiste) {
					skladiste.dodaj(elem);
					System.out.println("Proizvodjac ID: " + id + " je smestio element "
							+ elem);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("kraj rada proizvodjaca ID: " +id);
		}
	}
}
