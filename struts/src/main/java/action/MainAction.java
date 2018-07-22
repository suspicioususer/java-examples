package action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.MainForm;
import model.Account;
import model.User;

public class MainAction extends Action {
	
	private static final int PAGE_DEFAULT = 0;
	private static final int PAGE_DETAIL = 2;

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		MainForm mainForm = (MainForm) form;
		
		if(PAGE_DEFAULT == mainForm.getPage()){
			return performDefault(mapping, mainForm, request, response);
		} else if(PAGE_DETAIL == mainForm.getPage()){
			return performDetail(mapping, mainForm, request, response);
		} else {
			throw new Exception("Page not found!");
		}

	}
	
	public ActionForward performDefault(ActionMapping mapping, MainForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<User, ArrayList<Account>> values = new HashMap<User, ArrayList<Account>>();

		fillValues(values);
		
		form.setMessage("User & Account Informations");

		request.setAttribute("dataList", values);
		return mapping.findForward("default");
	}
	
	public ActionForward performDetail(ActionMapping mapping, MainForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		form.setMessage("Other Page");
		return mapping.findForward("detail");
	}

	private static ArrayList<User> createSampleUsers() {
		ArrayList<User> users = new ArrayList<User>();
		User u1 = new User(1, "user1", "pass1");
		User u2 = new User(2, "user2", "pass2");
		User u3 = new User(3, "user3", "pass3");
		users.add(u1);
		users.add(u2);
		users.add(u3);
		return users;
	}

	private static ArrayList<Account> createSampleAccounts() {

		ArrayList<Account> acc = new ArrayList<Account>();

		Account a1 = new Account(1, 0.0);
		Account a2 = new Account(2, 13.0);
		Account a3 = new Account(3, 66.6);
		Account a4 = new Account(4, -4.0);
		Account a5 = new Account(5, 20.0);
		Account a6 = new Account(6, 100.0);
		Account a7 = new Account(7, 99.0);
		Account a8 = new Account(8, 2000.0);
		Account a9 = new Account(9, 100000.0);
		Account a10 = new Account(10, 2000000.0);

		acc.add(a1);
		acc.add(a2);
		acc.add(a3);
		acc.add(a4);
		acc.add(a5);
		acc.add(a6);
		acc.add(a7);
		acc.add(a8);
		acc.add(a9);
		acc.add(a10);
		
		return acc;
	}
	
	private static void fillValues(HashMap<User, ArrayList<Account>> values){
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Account> accounts = new ArrayList<Account>();
		users = createSampleUsers();
		accounts = createSampleAccounts();
		
		ArrayList<Account> acc1 = new ArrayList<Account>();
		acc1.add(accounts.get(0));
		acc1.add(accounts.get(9));
		
		ArrayList<Account> acc2 = new ArrayList<Account>();
		acc2.add(accounts.get(1));
		acc2.add(accounts.get(7));
		acc2.add(accounts.get(8));
		
		ArrayList<Account> acc3 = new ArrayList<Account>();
		acc3.add(accounts.get(2));
		acc3.add(accounts.get(3));
		acc3.add(accounts.get(4));
		acc3.add(accounts.get(5));
		acc3.add(accounts.get(6));
		
		values.put(users.get(0), acc1);
		values.put(users.get(1), acc2);
		values.put(users.get(2), acc3);
	}
}