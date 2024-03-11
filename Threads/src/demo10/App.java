package demo10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
	private static int count=0;
	private static Lock lock=new ReentrantLock();
	private static Condition cond=lock.newCondition();
	public static void increment() {
		
		
		for(int i=0;i<10000;i++) {
			count++;
		}
		
	}
	
	public static  void firstThread() throws InterruptedException {
		lock.lock();
		System.out.println("Waiting......");
		cond.await();
		System.out.println("Resumed");
		try {
		increment();}
		finally {
		lock.unlock();}
	}
	
	public static void secondThread() throws InterruptedException{
		Thread.sleep(1000);
		lock.lock();
		System.out.println("Press return key");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key");
		cond.signal();
		try {
			increment();}
		finally {
			lock.unlock();
			
			}
		
	}
	
	public static void main(String[] args) {
		Thread t1=new Thread(new Runnable() {
			public void run() {
				try {
					firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}); 
		Thread t2=new Thread(new Runnable() {
			public void run() {
				try {
					secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		
		
		System.out.println("Value of count is: "+count);
		
	}

}
