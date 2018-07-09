package custom.models;

public class Account {
	
	private int accountNo;
	private double balance;
	//private User user;
	
	public Account(){
		
	}
	
	public Account(int accountNo, User user) {
		super();
		this.accountNo = accountNo;
		//this.user = user;
	}
	
	
	public Account(int accountNo, double balance, User user) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		//this.user = user;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		//return user;
		return null;
	}

	public void setUser(User user) {
		//this.user = user;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", balance=" + balance + "]";
	}
	
	
		

}
