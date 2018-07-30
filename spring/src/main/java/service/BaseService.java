package service;

import exception.CustomException;
import model.User;

public abstract class BaseService {

	public BaseService() {

	}

	public void checkUserDatas(User user) throws CustomException {
		checkPassword(user.getPassword());
	}

	public void checkPassword(String password) throws CustomException {
		if (password == null || password.isEmpty() || "".equals(password.trim()))
			throw new CustomException("Password can not be null.", 7006);

		if (password.length() < 8 || password.length() > 16)
			throw new CustomException("Password length error. [Min: 8, MAX: 16] characters.", 7007);
	}

	public void checkUser(User user) throws CustomException {
		if (user == null)
			throw new CustomException("Username or password is wrong.", 7021);
	}

}
