package demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App2 {
	
	private Random random=new Random();
	private List<Integer> list1=new ArrayList<>();
	private List<Integer> list2=new ArrayList<>();
	
	
	public synchronized void stageOne() {
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list1.add(random.nextInt(100));
		
	}
	
	public synchronized void stageTwo() {
		 
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list2.add(random.nextInt(100));
		
	}
	
	public void process() {
		for(int i=0;i<1000;i++) {
			stageOne();
			stageTwo();
		}
	}
	
	public void doWork() {
		System.out.println("Starting.....");
		
		long start=System.currentTimeMillis();
		
		Thread t1=new Thread(new Runnable() {
			public void run() {
				process();
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			public void run() {
				process();
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		long end=System.currentTimeMillis();
		
		System.out.println("Time Taken"+(end-start));
		System.out.println("length of list1 "+list1.size()+"; length of list2 "+list2.size());
	}
	
	public static void main(String[] args) {
		App app=new App();
		app.doWork();
	}

}
