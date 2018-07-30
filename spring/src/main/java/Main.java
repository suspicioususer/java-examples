//import org.hibernate.Session;
//
//import model.Account;
//import model.User;
//import util.HibernateUtil;
//
//public class Main {
//
//	public static void main(String[] args) {
//		System.out.println("::INITALIZED::");
//		Session session = HibernateUtil.getSessionFactory().openSession();
//
//		session.beginTransaction();
//
//		User user = new User();
//		user.setUserName("user1");
//		user.setPassword("pass1");
//		session.save(user);
//
//		Account account1 = new Account();
//		account1.setBalance(4.39);
//		
//		Account account2 = new Account();
//		account2.setBalance(-75.0);
//		
//		Account account3 = new Account();
//		account3.setBalance(12500000.0);
//
//		account1.setUser(user);
//		account2.setUser(user);
//		account3.setUser(user);
//		
//		user.getAccounts().add(account1);
//		user.getAccounts().add(account2);
//		user.getAccounts().add(account3);
//		
//		session.save(account1);
//		session.save(account2);
//		session.save(account3);
//
//		session.getTransaction().commit();
//		System.out.println("::ENDED::");
//
//	}
//
//}
