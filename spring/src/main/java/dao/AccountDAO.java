package dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Account;
import model.User;

@Repository
public class AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Account addAccount(Account account) {
		getCurrentSession().save(account);
		return account;
	}

	public List<Account> getAccounts() {
		return getCurrentSession().createQuery("from Account").list();
	}

	public void deleteAccount(int ID) {
		Account a = getAccountByID(ID);
		if(a != null){
			getCurrentSession().delete(a);
		}
	}

	public Account updateAccount(int ID, Account account) {
		getCurrentSession().update(account);
		return account;
	}

	public List<Account> getAccountsByUser(User user) throws NoSuchAlgorithmException {
		List<Account> accounts = getCurrentSession().createQuery("from Account where uID = :uID").setParameter("uID", user.getID())
				.list();
		if (accounts.isEmpty()) {
			return null;
		} else {
			return accounts;
		}
	}

	public Account getAccountByID(int ID) {
	
		Account account = (Account) getCurrentSession().createQuery("from Account where aID = :aID").setParameter("aID", ID).list();
		//Account account = (Account) getCurrentSession().get(Account.class, ID);
		return account;
	}

}
