package demo14;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Started");
		Thread t1=new Thread(new Runnable() {
			
			public void run() {
				for(int i=0;i<1E9;i++) {
					/**
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("Thread is interrupted");
						break;
					}
					**/
					try {
						Thread.sleep(1);
					}
					catch(InterruptedException e) {
						System.out.println(e);
						break;
					}
				}
			}
			
		});
		
		t1.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.interrupt();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finised");

	}

}
