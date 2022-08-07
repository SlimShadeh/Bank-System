package test;

import java.util.Scanner;

import skladiste.Potrosac;
import skladiste.Proizvodjac;
import skladiste.Skladiste;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		Skladiste s=new Skladiste(10);
		
		Proizvodjac proizvodjaci[]= new Proizvodjac[5];
		Potrosac potrosaci[]=new Potrosac[2];
		
		for(int i=0;i<5;i++) {
			(proizvodjaci[i]=new Proizvodjac(s,i)).start();
			
			if(i<2) {
				(potrosaci[i]=new Potrosac(s,i)).start();
			}
		}
		
		new Scanner(System.in).nextLine();
		
		for(int i=0;i<5;i++) {
			proizvodjaci[i].interrupt();
			if(i<2) potrosaci[i].interrupt();
		}
		
		for(int i=0;i<5;i++) {
			proizvodjaci[i].join();
			if(i<2) potrosaci[i].join();
		}
		
		System.out.println("kraj programa");
	}
}
