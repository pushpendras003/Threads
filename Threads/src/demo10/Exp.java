package demo10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exp {
	
	public static int  count=0;
	public static Lock lock=new ReentrantLock();
	public static void increment() {
		for(int i=0;i<10000;i++) {
			count++;
		}
	}
	public static void firstThread() {
		lock.lock();
		try {
		increment();}
		finally {
		lock.unlock();}
	}
	
	
	public static void secondThread() {
		lock.lock();
		try {
		increment();}
		finally {
		lock.unlock();}
	}
	
	public static void main(String[] args) {
		
		Thread t1=new Thread(new Runnable() {
			public void run() {
				firstThread();
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			public void run() {
				secondThread();
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
		System.out.println("Count is : "+count);
		
		
		

	}

}
