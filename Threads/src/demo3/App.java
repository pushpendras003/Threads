package demo3;

/*
 * 
 * @author pushpendrasingh
 * Synchorized key word handles the inherent lock of the object ensuring when a thread has access of the lock no other thread can 
 * access the variable 
 */

public class App {
	
	public int count=0;
	
	public synchronized void  increment() {
		count++;
	}

	public static void main(String[] args) {
		
		App var=new App();
		var.doWork();
		

	}
	
	public void doWork() {
		Thread t1=new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<10000;i++) {
					increment();
				}
			}
		});
		Thread t2=new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<10000;i++) {
					increment();
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
		
		System.out.println(count);
	}
	
	

}
