package custom.threads;

import custom.CommonValues;
import custom.ops.CipherOperations;

public class PasswordDecryptor extends Thread {

	private CipherOperations cipherOperations;
	private CommonValues commonValues;

	public PasswordDecryptor(CipherOperations cipherOperations, CommonValues commonValues) {
		this.cipherOperations = cipherOperations;
		this.commonValues = commonValues;
	}

	@Override
	public void run() {
		try {
			synchronized (commonValues) {
				for (int i = 0; i < 3; i++) {
					while (commonValues.isState()) {
						commonValues.wait();
					}
					System.out.println("# " + this.getClass().getSimpleName() + " #");
					System.out.println("\tEncrypted Value: " + commonValues.getResult());
					String temp = cipherOperations.decryptor3DES(commonValues.getResult());
					System.out.println("\tDecrypted Value: " + temp);
					Thread.sleep(1000);
					commonValues.setState(true);
					commonValues.notify();
				}
			}
		} catch (Exception e) {
			System.out.println("Thread error occurred!\n" + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
