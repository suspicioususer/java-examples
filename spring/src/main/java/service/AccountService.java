package service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AccountDAO;
import model.Account;
import model.User;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountDAO accountDAO;
	private User user;

	public Account addAccount(Account account) throws Exception {
		account.setUser(user);
		return accountDAO.addAccount(account);
	}

	public Account updateAccount(Account account) {
		account.setUser(user);
		return accountDAO.updateAccount(account.getID(), account);
	}

	public Account getAccountByID(int ID) {
		return accountDAO.getAccountByID(ID);
	}

	public void deleteAccount(int ID) {
		accountDAO.deleteAccount(ID);
	}

	public List<Account> getAccountsByUser(User user) throws NoSuchAlgorithmException {
		return accountDAO.getAccountsByUser(user);
	}

	public List<Account> getAccounts() {
		return accountDAO.getAccounts();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
