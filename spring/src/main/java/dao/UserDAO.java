package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import exception.CustomException;
import model.User;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public User addUser(User user) throws CustomException, NoSuchAlgorithmException {
		boolean userDuplicate = userAlreadyExists(user.getUserName());

		if (userDuplicate) {
			throw new CustomException("User already exists.", 7015);
		}

		String hashedPassword = passwordHasher(user.getPassword());
		user.setPassword(hashedPassword);
		getCurrentSession().save(user);
		return user;

	}

	public List<User> getUsers() {
		List<User> users = getCurrentSession().createQuery("from User").list();
		return users;
	}

	public void deleteUser(int ID) {
		User user = new User();
		user.setID(ID);
		getCurrentSession().delete(user);
	}

	public User updateUser(int ID, User user) {
		getCurrentSession().update(user);
		return user;
	}

	private boolean userAlreadyExists(String userName) {
		List<User> users = getCurrentSession().createQuery("from User where userName = :userName")
				.setParameter("userName", userName).list();
		if (users.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public User getUser(String userName, String password) throws NoSuchAlgorithmException {
		String hashedPassword = passwordHasher(password);
		List<User> users = getCurrentSession().createQuery("from User where userName = :userName and password = :password")
				.setParameter("userName", userName).setParameter("password", hashedPassword).list();
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	public User getUserByID(int ID) {
		List<User> users = getCurrentSession().createQuery("from User where userID = :userID").setParameter("userID", ID).list();
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	private String passwordHasher(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] hash = messageDigest.digest(password.getBytes());
		String hashedPassword = new String(hash);
		return hashedPassword;
	}

	public String newPassword(String password) throws NoSuchAlgorithmException {
		String newPassword = passwordHasher(password);
		return newPassword;
	}
}
