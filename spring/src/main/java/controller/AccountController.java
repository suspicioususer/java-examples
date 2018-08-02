package controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import exception.CustomException;
import model.Account;
import model.User;
import service.AccountService;
import service.UserService;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView accountsMainView(@RequestParam(value = "userID", required = false) Integer ID)
			throws CustomException {
		ModelAndView modelAndView = new ModelAndView("accounts");
		System.out.println("ID: " + ID);
		User user;
		if (ID != null && ID != 0) {
			user = userService.getUserByID(ID);
		} else {
			user = accountService.getUser();
		}
		accountService.setUser(user);
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addAccountView() {
		ModelAndView modelAndView = new ModelAndView("add-account");
		modelAndView.addObject("account", new Account());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addAccountView(@ModelAttribute Account account) {

		ModelAndView modelAndView = new ModelAndView("accounts");
		String message = "Account was successfully added.";
		try {
			accountService.addAccount(account);
			message = "Account was successfully added.";
		} catch (Exception e) {
			message = "Account has not been added!";
			e.printStackTrace();
		}
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	/*
	 * @RequestMapping(value = "/list") public ModelAndView listAccountsView() {
	 * ModelAndView modelAndView = new ModelAndView("list-accounts");
	 * 
	 * List<Account> accounts = accountService.getAccounts();
	 * modelAndView.addObject("accounts", accounts);
	 * 
	 * return modelAndView; }
	 */

	@RequestMapping(value = "/list")
	public ModelAndView listAccountsView() throws NoSuchAlgorithmException {
		ModelAndView modelAndView = new ModelAndView("list-accounts");
		List<Account> accounts = accountService.getAccountsByUser(accountService.getUser());
		modelAndView.addObject("accounts", accounts);

		return modelAndView;
	}

	@RequestMapping(value = "/edit/{ID}", method = RequestMethod.GET)
	public ModelAndView editAccountView(@PathVariable Integer ID) {
		ModelAndView modelAndView = new ModelAndView("edit-account");
		System.out.println("Account ID [EDIT]: " + ID);
		Account account = accountService.getAccountByID(ID);
		System.out.println("Account [EDIT]: " + account.toString());
		modelAndView.addObject("account", account);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{ID}", method = RequestMethod.POST)
	public ModelAndView editAccount(@ModelAttribute Account account, @PathVariable Integer ID) {

		ModelAndView modelAndView = new ModelAndView("accounts");
		System.out.println("[pEdit] ID: " + ID);
		System.out.println("[pEdit] Account <ID>: " + account.getID());
		System.out.println("[pEdit] Account <BALANCE>: " + account.getBalance());
		
		account.setUser(accountService.getUser());
		
		accountService.updateAccount(account);

		String message = "Account was successfully edited.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@RequestMapping(value = "/delete/{ID}", method = RequestMethod.GET)
	public ModelAndView deleteAccount(@PathVariable Integer ID) {
		ModelAndView modelAndView = new ModelAndView("accounts");
		System.out.println("[delete] ID: " + ID);
		accountService.deleteAccount(ID);
		String message = "Account was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
