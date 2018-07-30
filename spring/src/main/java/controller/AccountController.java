package controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.Account;
import model.User;
import service.AccountService;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

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

	/*@RequestMapping(value = "/list")
	public ModelAndView listAccountsView() {
		ModelAndView modelAndView = new ModelAndView("list-accounts");

		List<Account> accounts = accountService.getAccounts();
		modelAndView.addObject("accounts", accounts);

		return modelAndView;
	}*/
	
	@RequestMapping(value = "/list")
	public ModelAndView listAccountsView() throws NoSuchAlgorithmException {
		ModelAndView modelAndView = new ModelAndView("list-accounts");
		User u = new User("admin","6999");
		List<Account> accounts = accountService.getAccountsByUser(u);
		modelAndView.addObject("accounts", accounts);

		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editTeamPage(@PathVariable Integer ID) {
		ModelAndView modelAndView = new ModelAndView("edit-account");
		Account account = accountService.getAccountByID(ID);
		modelAndView.addObject("account", account);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView edditingTeam(@ModelAttribute Account account, @PathVariable Integer ID) {

		ModelAndView modelAndView = new ModelAndView("accounts");

		accountService.updateAccount(account);

		String message = "Account was successfully edited.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteTeam(@PathVariable Integer ID) {
		ModelAndView modelAndView = new ModelAndView("accounts");
		accountService.deleteAccount(ID);
		String message = "Account was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
