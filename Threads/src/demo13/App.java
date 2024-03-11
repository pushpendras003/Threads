package demo13;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) {
	
		ExecutorService executor=Executors.newCachedThreadPool();
		Future<Integer> future=executor.submit(new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				Random random=new Random();
				System.out.println("Started");
				int val=random.nextInt(5000);
				if(val>2000) {
					throw new  IOException("An Execption Occured");
				}
				Thread.sleep(val);
				
				
				
				return val;
			}
			
		}
				);
		
		executor.shutdown();
		
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( ExecutionException e) {
			System.out.println(e.getCause().getMessage());
		}
		
		

	}

}
