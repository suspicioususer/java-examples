package custom.threads;

import java.util.List;

import custom.CommonValues;
import custom.models.User;

public class UserProcessor extends Thread {

	private List<User> users = null;
	private CommonValues commonValues;

	public UserProcessor(List<User> users, CommonValues commonValues) {
		this.users = users;
		this.commonValues = commonValues;
	}

	@Override
	public void run() {
		try {
			synchronized (commonValues) {
				for (int i = 0; i < users.size(); i++) {
					while (!commonValues.isState()) {
						commonValues.wait();
					}

					System.out.println("# " + this.getClass().getSimpleName() + " #");
					User user = users.get(i);
					System.out.println("\t" + user.toString());
					commonValues.setResult(user.getCipheredPassword());
					Thread.sleep(3000);
					commonValues.setState(false);
					commonValues.notify();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
