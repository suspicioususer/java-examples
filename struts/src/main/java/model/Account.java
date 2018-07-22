package model;

public class Account {
	
	private int ID;
	private double balance;
	
	public Account(int ID, double balance) {
		this.ID = ID;
		this.balance = balance;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

}
