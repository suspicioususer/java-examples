package custom.models;

import java.util.List;

public class User {

	private int ID;
	private String userName;
	private String password;
	private String cipheredPassword;
	private List<Account> accounts;

	public User() {

	}

	public User(int ID, String userName, String password) {
		this(ID,userName,password,"CIPHERED_PASSWORD");
	}

	public User(int ID, String userName, String password, List<Account> accounts) {
		this.ID = ID;
		this.userName = userName;
		this.password = password;
		this.accounts = accounts;
	}

	private User(int ID, String userName, String password, String cipheredPassword) {
		this.ID = ID;
		this.userName = userName;
		this.password = password;
		this.cipheredPassword = cipheredPassword;
	}

	private User(int ID, String userName, String password, String cipheredPassword, List<Account> accounts) {
		this.ID = ID;
		this.userName = userName;
		this.password = password;
		this.cipheredPassword = cipheredPassword;
		this.accounts = accounts;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCipheredPassword() {
		return cipheredPassword;
	}

	public void setCipheredPassword(String cipheredPassword) {
		this.cipheredPassword = cipheredPassword;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", userName=" + userName + ", password=" + password + ", cipheredPassword="
				+ cipheredPassword + ", accounts=" + accounts.toString() + "]";
	}

}
