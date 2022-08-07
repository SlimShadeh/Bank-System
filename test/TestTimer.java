package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

public class TestTimer {

	public static class Timer extends Thread{
		
		private HashMap<Runnable,Integer> klijenti=new HashMap<>();
		
		public void dodajKlijenta(Runnable r,int sekundi) {
			klijenti.put(r,sekundi);
		}
		
		@Override
		public void run() {
		
			try {
				while(true) {
					LocalDateTime vreme = LocalDateTime.now();
					DateTimeFormatter formatter=
							DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
					System.out.println(formatter.format(vreme));
					
					Thread.sleep(1000);
					
					synchronized (this) {
						Iterator<Entry<Runnable,Integer>> iter =klijenti.entrySet().iterator();
						while(iter.hasNext()) {
							Entry<Runnable,Integer> entry=iter.next();
							Runnable r=entry.getKey();
							Integer sekundi=entry.getValue();
							if(sekundi==1) {
								synchronized (r) {
									r.notify();
								}
							}
							else {
								entry.setValue(sekundi -1);
							}
						}
					}
				}
			}catch(InterruptedException e) {
				
			}
		}
	}
	
	public static void main(String[] args) {
		Timer timer=new Timer();
		timer.start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					synchronized (this) {
						timer.dodajKlijenta(this, 3);
						try {
							this.wait();
						} catch (InterruptedException e) {}
						System.out.println("Proslo je 3s...");
					}
					
				}
			};
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					synchronized (this) {
						timer.dodajKlijenta(this, 5);
						try {
							this.wait();
						} catch (InterruptedException e) {}
						System.out.println("Proslo je 5s...");
					}
					
				}
			};
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					synchronized (this) {
						int vreme=1+ new Random().nextInt(5);
						System.out.println("!!!!!!!!!!Ceka se " +vreme +"s...");
						timer.dodajKlijenta(this, vreme);
						
						try {
							this.wait();
						} catch (InterruptedException e) {}
						System.out.println("!!!!!!!!!!Proslo je " + vreme+"s.");
					}
					
				}
			};
		}).start();
	}
}
