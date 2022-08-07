package skladiste;

public class Skladiste {

	private int niz[];
	private int kap,br;
	private int pocetak,kraj;
	
	public Skladiste(int k) {
		niz=new int[kap=k];
		this.pocetak=this.kraj=this.br=0;
	}
	
	public synchronized void dodaj(int i) throws InterruptedException {
		while(br==kap) this.wait();
		niz[kraj++]=i;
		if(kraj==kap) kraj=0;
		br++;
		this.notifyAll();
	}
	
	public synchronized int uzmi() throws InterruptedException {
		while(br==0) this.wait();
		int elem=niz[pocetak];
		pocetak=(pocetak+1)%kap;
		br--;
		this.notifyAll();
		return elem;
		
	}
}
