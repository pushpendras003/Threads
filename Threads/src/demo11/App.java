package demo11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
	
	Account acc1=new Account();
	Account acc2=new Account();
	
	private Lock lock1 =new ReentrantLock();
	private Lock lock2 =new ReentrantLock();
	
	public void acquireLock(Lock lock1,Lock lock2) throws InterruptedException {
		
		boolean firstLock=false;
		boolean secondLock=false;
		
		while(true) {
			try {
			firstLock=lock1.tryLock();
			secondLock=lock2.tryLock();
			}
			finally {
				if(firstLock && secondLock) {
					return;
					
				}
				if(firstLock) {
					lock1.unlock();
				}
				if(secondLock) {
					lock2.unlock();
				}
			}
			Thread.sleep(1);
			
			
		}
		
		
		
	}
	public void firstThread() throws InterruptedException {
		Random random=new Random();
		acquireLock(lock1,lock2);
		
		for (int i=0;i<10000;i++) {
			Account.transfer(acc1,acc2,random.nextInt(100));
		}
		lock1.unlock();
		lock2.unlock();
		
	}
	
	public void secondThread() throws InterruptedException {
		
		Random random=new Random();
		acquireLock(lock1,lock2);
		
		
		
		for (int i=0;i<10000;i++) {
			Account.transfer(acc2,acc1,random.nextInt(100));
		}
		
		lock1.unlock();
		lock2.unlock();
		
		
	}
	
	public void afterAllTrans() {
		System.out.println("Account1 balace is "+acc1.getbalance());
		System.out.println("Account1 balace is "+acc2.getbalance());
		System.out.println("Total balance is : "+(acc2.getbalance()+acc1.getbalance()));
	}

}
