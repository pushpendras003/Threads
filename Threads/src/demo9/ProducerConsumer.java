package demo9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class ProducerConsumer {
	public List<Integer> list=new ArrayList<Integer>();
	public int Limit=10;
	public Object lock=new Object();
	
	public void producer() throws InterruptedException {
		int val=0;
		
		while(true) {
			synchronized(lock) {
			while(list.size()==Limit) {
				lock.wait();
			}
			list.add(val++);
			lock.notify();
		}}
	}
	
	public void consumer() throws InterruptedException {
		
		Random random=new Random();
		
		while(true) {
			synchronized(lock) {
			while(list.size()==0) {
				lock.wait();
			}
			System.out.println("Size of the list is "+list.size());
			int value=list.remove(0);
			System.out.println("value is "+value);
			lock.notify();
		}
		Thread.sleep(random.nextInt(100));
		}
	}
	
	
	public static void main(String[] args) {
		
		ProducerConsumer app=new ProducerConsumer();
		new Thread(
			new Runnable() {
				public void run() {
					try {
						app.producer();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		).start();
		
		new Thread(
				new Runnable() {
					public void run() {
						try {
							app.consumer();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			).start();
		
	}
}
