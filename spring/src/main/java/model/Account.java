package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account", catalog = "springtest")
public class Account {

	@Id
	@Column(name = "accountID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;

	@Column(name = "balance", nullable = false, columnDefinition = "double default 0.0")
	private double balance;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userID")
	private User user;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [id=" + ID + ", balance=" + balance + ", user=" + user.toString() + "]";
	}

}
