package test;

import java.util.Scanner;

import banka.Bankomat;
import banka.Plata;
import banka.Racun;

public class TestBanka {

	public static void main(String[] args) {
		Racun racun=new Racun(0);
		
		Bankomat bankomat=new Bankomat(racun);
		Bankomat bankomat2=new Bankomat(racun);
		Plata plata=new Plata(racun);
		
		bankomat.start();
		bankomat2.start();
		plata.start();
		
		new Scanner(System.in).nextLine();
		bankomat.interrupt();
		bankomat2.interrupt();
		plata.interrupt();
		
		try {
			bankomat.join();
			bankomat2.join();
			plata.join();
		} catch (InterruptedException e) {}
		
		int ukupnaUplata=plata.dohvatiStanje();
		int ukupnaIsplata=bankomat.dohvatiStanje()+bankomat2.dohvatiStanje();
		int stanje = racun.dohvatiStanje();
		
		System.out.println("\n\n");
		System.out.println("Ukupna isplata: " + ukupnaIsplata);
		System.out.println("Ukupna uplata: " + ukupnaUplata);
		System.out.println("Razlika uplate i isplate: " + (ukupnaIsplata+ ukupnaUplata));
		System.out.println("stanje na racunu: " +stanje);
		}
}
