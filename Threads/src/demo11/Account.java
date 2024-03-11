package demo11;

public class Account {
	private int balance=10000;
	
	public void withdraw(int val) {
		this.balance-=val;
	}
	
	public void deposit(int val) {
		this.balance+=val;
	}
	
	public static void transfer(Account acc1,Account acc2,int val) {
		acc1.withdraw(val);
		acc2.deposit(val);
	}

	public int getbalance() {
		// TODO Auto-generated method stub
		return this.balance;
	}

}
