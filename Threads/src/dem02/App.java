package dem02;

import java.util.Scanner;

class Runner extends Thread{
	private volatile boolean running=true;
	public void run() {
		while(running) {
			System.out.println("Hello");
			
		}
	}
	
	public void shutdown() {
		running=false;
	}
}
public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runner r=new Runner();
		r.start();
		
		System.out.println("enter return to set the running to false");
		Scanner sc=new Scanner(System.in);
		sc.nextLine();
		r.shutdown();
		
		
	}

}
