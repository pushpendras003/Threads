package demo11;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		App app=new App();
		Thread t1=new Thread(new Runnable()
				{
					public void run() {
						try {
							app.firstThread();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				);
		Thread t2=new Thread(new Runnable()
		{
			public void run() {
				try {
					app.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		);
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		app.afterAllTrans();
		
		

	}

}
