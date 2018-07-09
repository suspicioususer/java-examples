package custom;

import java.util.ArrayList;
import java.util.List;

import custom.models.Account;
import custom.models.User;
import custom.ops.CipherOperations;
import custom.ops.FileOperations;
import custom.ops.JSONOperations;
import custom.threads.PasswordDecryptor;
import custom.threads.UserProcessor;

public class Main {

	private static List<User> users = new ArrayList<User>();

	public static void main(String[] args) {
		int shiftCount = 7;
		CommonValues commonValues = new CommonValues();
		FileOperations fileOps = new FileOperations();
		CipherOperations cipherOps = new CipherOperations();

		try {
			cipherOps.initialize3DES();
		} catch (Exception e) {
			System.out.println("3DES has not been initialized!");
			e.printStackTrace();
		}
		 createSampleUsers(cipherOps , shiftCount);
		 createSampleAccounts();
		 decryptedResults(cipherOps, shiftCount);

		 String jsonString = JSONOperations.getInstance().getJSONString(users);
		 fileOps.writeToFile(jsonString, false);

		String fileContent = fileOps.readFromFile();
		System.out.println(fileContent);
		List<User> us = JSONOperations.getInstance().convertJSONString(fileContent, true);
		us.forEach(System.out::println);

		UserProcessor up = new UserProcessor(us, commonValues);
		PasswordDecryptor pd = new PasswordDecryptor(cipherOps, commonValues);
		
		up.start();
		pd.start();
		
		try {
			up.join();
			pd.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("Main program exited.");

	}

	private static void createSampleUsers(CipherOperations cipherOps, int shiftCount) {
		User u1 = new User(1, "user1", "pass1");
		User u2 = new User(2, "user2", "pass2");
		User u3 = new User(3, "user3", "pass3");

		// u1.setCipheredPassword(cipherOps.encryptorShiftCipher(u1.getPassword(),
		// shiftCount));
		// u2.setCipheredPassword(cipherOps.encryptorShiftCipher(u2.getPassword(),
		// shiftCount));
		// u3.setCipheredPassword(cipherOps.encryptorShiftCipher(u3.getPassword(),
		// shiftCount));

		u1.setCipheredPassword(cipherOps.encryptor3DES(u1.getPassword()));
		u2.setCipheredPassword(cipherOps.encryptor3DES(u2.getPassword()));
		u3.setCipheredPassword(cipherOps.encryptor3DES(u3.getPassword()));

		users.add(u1);
		users.add(u2);
		users.add(u3);

	}

	private static void createSampleAccounts() {

		List<Account> acc1 = new ArrayList<Account>();
		List<Account> acc2 = new ArrayList<Account>();
		List<Account> acc3 = new ArrayList<Account>();

		Account a1 = new Account(1, 0.0, users.get(0));
		Account a2 = new Account(2, 13.0, users.get(0));
		Account a3 = new Account(3, 66.6, users.get(0));

		acc1.add(a1);
		acc1.add(a2);
		acc1.add(a3);
		users.get(0).setAccounts(acc1);

		Account a4 = new Account(4, -4.0, users.get(1));
		Account a5 = new Account(5, 20.0, users.get(1));
		Account a6 = new Account(6, 100.0, users.get(1));

		acc2.add(a4);
		acc2.add(a5);
		acc2.add(a6);
		users.get(1).setAccounts(acc2);

		Account a7 = new Account(7, 99.0, users.get(2));
		Account a8 = new Account(8, 2000.0, users.get(2));
		Account a9 = new Account(9, 100000.0, users.get(2));

		acc3.add(a7);
		acc3.add(a8);
		acc3.add(a9);
		users.get(2).setAccounts(acc3);
	}

	private static void decryptedResults(CipherOperations cipherOps, int shiftCount) {
		users.forEach(x -> {
			String eText = x.getCipheredPassword();
			System.out.println("ID: " + x.getID());
			// System.out.println("Encrypted Text: " + eText);
			// System.out.println("Decrypted Text: " +
			// cipherOps.decryptorShiftCipher(eText, shiftCount));
			System.out.println("Encrypted Text: " + eText);
			System.out.println("Decrypted Text: " + cipherOps.decryptor3DES(eText));
		});
	}
}
