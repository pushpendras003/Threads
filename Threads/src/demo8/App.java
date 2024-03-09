package demo8;

import java.util.Scanner;

public class App {
	
	public boolean produced=false;
	
	Object lock=new Object();
	
	public  void producer() throws InterruptedException {
		synchronized(lock) {
		System.out.println("Process started");
		lock.wait();
		//lock.notify();
		//System.out.println("Ready for consumption");
		System.out.println("process ended");
		
		}
		
	}
	
	public void consumer() throws InterruptedException {
		Scanner sc=new Scanner(System.in);
		synchronized(lock) {
		System.out.println("Consumed some items");
		System.out.println("Press return to produce");
		sc.nextLine();
		lock.notify();
		Thread.sleep(500);
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		App app=new App();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					app.producer();
										
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start(); 
		new Thread(new Runnable() {
			public void run() {
				try {
					app.consumer();
										
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
	
		
		
	}
}
