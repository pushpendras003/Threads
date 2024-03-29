package demo5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
	private int id;
	public Processor(int id) {
		this.id=id;
	}
	public void run() {
		System.out.println("Starting "+id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed "+id);
		
	}
	
}
public class App {
	
	public static void main(String[] args) {
		
		ExecutorService executor=Executors.newFixedThreadPool(2);
		
		for(int i=0;i<200;i++) {
			executor.submit(new Processor(i));
		}
		
		executor.shutdown();
		
		System.out.println("All tasks are submitted");
		
		try {
			executor.awaitTermination(1,TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks are completed");
		
	}
}
