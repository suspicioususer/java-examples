package controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import exception.CustomException;
import model.User;
import service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(){
		return new ModelAndView("login", "user", new User());
	}
	
	@RequestMapping(value = "/pLogin", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("userName") String userName, @RequestParam("password") String password){
		User user;
		try {
			user = userService.getUserByData(userName, password);
			return new ModelAndView("accounts", "user", user);
		}  catch (CustomException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new ModelAndView("error", "exception", e.getMessage());
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("register", "user", new User());
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@RequestParam("userName") String userName, @RequestParam("password") String password) {
		User user = new User(userName, password);
		try {
			userService.checkUserDatas(user);
			userService.addUser(user);
			return new ModelAndView("redirect:/login");
		} catch (CustomException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new ModelAndView("error", "exception", e.getMessage());
		}		
	}

}
