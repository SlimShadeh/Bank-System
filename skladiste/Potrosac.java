package skladiste;

import java.util.Random;

public class Potrosac extends Thread {

	private Skladiste skladiste;
	private int id;
	
	public Potrosac(Skladiste skladiste,int id) {
		this.skladiste=skladiste;
		this.id=id;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
			
				Thread.sleep(1000+ new Random().nextInt(3000));
				
				synchronized (skladiste) {
					int elem=skladiste.uzmi();
					System.out.println("Potrosac ID: " + id + " je dohvatio element "
							+ elem);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("kraj rada potrosaca ID." + id);
		}
	}
}