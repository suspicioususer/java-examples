//package dao;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.List;
//
//import org.hibernate.Session;
//import org.springframework.stereotype.Component;
//
//import exception.CustomException;
//import model.User;
//import util.HibernateUtil;
//
//@Component
//public class UserDAO {
//
//	public User addUser(User user) throws CustomException, NoSuchAlgorithmException {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		boolean userDuplicate = userAlreadyExists(user.getUserName());
//
//		if (userDuplicate) {
//			throw new CustomException("User already exists.", 7015);
//		}
//
//		String hashedPassword = passwordHasher(user.getPassword());
//		user.setPassword(hashedPassword);
//		session.beginTransaction();
//		addUser(session, user);
//		session.getTransaction().commit();
//		session.close();
//		return user;
//
//	}
//
//	private void addUser(Session session, User user) {
//		session.save(user);
//	}
//
//	public List<User> getUsers() {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		List<User> users = session.createQuery("from User").list();
//		session.close();
//		return users;
//	}
//
//	public void deleteUser(int ID) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		User user = new User();
//		user.setID(ID);
//		session.beginTransaction();
//		session.delete(user);
//		session.getTransaction().commit();
//		session.close();
//	}
//
//	public User updateUser(int ID, User user) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		session.update(user);
//		session.getTransaction().commit();
//		session.close();
//		return user;
//	}
//
//	private boolean userAlreadyExists(String userName) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		List<User> users = session.createQuery("from User where userName = :userName")
//				.setParameter("userName", userName).list();
//		session.close();
//		if (users.isEmpty()) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//	public User getUser(String userName, String password) throws NoSuchAlgorithmException {
//		String hashedPassword = passwordHasher(password);
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		List<User> users = session.createQuery("from User where userName = :userName and password = :password")
//				.setParameter("userName", userName).setParameter("password", hashedPassword).list();
//		session.close();
//		if (users.isEmpty()) {
//			return null;
//		} else {
//			return users.get(0);
//		}
//	}
//
//	public User getUserByID(int ID) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		List<User> users = session.createQuery("from User where uID = :uID").setParameter("uID", ID).list();
//		session.close();
//		if (users.isEmpty()) {
//			return null;
//		} else {
//			return users.get(0);
//		}
//	}
//
//	private String passwordHasher(String password) throws NoSuchAlgorithmException {
//		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//		byte[] hash = messageDigest.digest(password.getBytes());
//		String hashedPassword = new String(hash);
//		return hashedPassword;
//	}
//
//	public String newPassword(String password) throws NoSuchAlgorithmException {
//		String newPassword = passwordHasher(password);
//		return newPassword;
//	}
//}
