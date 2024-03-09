package demo6;

/**
 * So the thing countdownlatch does is that suppose there are certain task to be executed and you know the count of repetition or sum of task
 * activity. Then you can define a count down latch which will keep track of task being executed irrespective of which thread is executing the
 * for you out of all the thread.
 * This can come handy in various scenario, especially where we have to execute a repetitive task asynchronously then we can leverage multiple 
 * threads with help of countdownlatch.
 * 
 * 
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
	CountDownLatch latch;
	
	public Processor(CountDownLatch latch) {
		this.latch=latch;
	}
	@Override
	public void run() {
		System.out.println("Task started");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		latch.countDown();
	}
	
}

public class App {
	public static void main(String[] args) {
		CountDownLatch latch=new CountDownLatch(3);
		ExecutorService executor=Executors.newFixedThreadPool(3);
		
		for(int i=0;i<3;i++) {
			executor.submit(new Processor(latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed");
	}

}
