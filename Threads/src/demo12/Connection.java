package demo12;

import java.util.concurrent.Semaphore;

public class Connection {
	
	public static Connection instance=new Connection();
	
	private Semaphore sem=new Semaphore(10);
	
	private int count=0;
	
	private Connection() {}
	
	public static Connection getInstance() {
		return instance;
	}
	
	public void connect() {
		int c=0;
		try {
			sem.acquire();
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			doConnect();
		}
		finally {
			sem.release();
			
		}
	}
	
	public void doConnect() {
		
		
		synchronized(this) {
			count++;
			
			System.out.println("Current connections:"+count);
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized(this) {
			count--;
			System.out.println("Current connections:"+count);
		}
		
	}

}
