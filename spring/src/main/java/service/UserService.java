//package service;
//
//import java.security.NoSuchAlgorithmException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import dao.UserDAO;
//import exception.CustomException;
//import model.User;
//
//@Service("userService")
//@Transactional
//public class UserService extends BaseService {
//
//	@Autowired
//	private UserDAO userDAO;
//
//	public UserService() {
//		userDAO = new UserDAO();
//	}
//
//	public User getUserByID(int ID) throws CustomException {
//		User user = userDAO.getUserByID(ID);
//		super.checkUser(user);
//		return user;
//	}
//	
//	public User getUserByData(String userName, String password) throws NoSuchAlgorithmException, CustomException {
//		User user = userDAO.getUser(userName, password);
//		super.checkUser(user);
//		return user;
//	}
//
//	public User addUser(User user) throws NoSuchAlgorithmException, CustomException {
//		checkUserDatas(user);
//		return userDAO.addUser(user);
//	}
//	
//	public User updateUser(User user) throws CustomException{
//		checkUserDatas(user);
//		return userDAO.updateUser(user.getID(), user);
//	}
//	
//	public void deleteUser(int ID){
//		userDAO.deleteUser(ID);
//	}
//	
//}
