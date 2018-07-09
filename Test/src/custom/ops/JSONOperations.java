package custom.ops;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import custom.models.User;

public class JSONOperations {

	private static JSONOperations jsonOps;

	public static JSONOperations getInstance() {

		if (jsonOps == null) {
			jsonOps = new JSONOperations();
		}
		return jsonOps;
	}

	public String getJSONString(User user) {
		return new Gson().toJson(user);
	}

	/*
	 * public String getJSONString(User user, boolean complex) { if (complex) {
	 * Type type = new TypeToken<User>() { }.getType(); return new
	 * Gson().toJson(user, type); } System.out.println("Not valid!"); return
	 * null; }
	 */

	public String getJSONString(List<User> users) {
		return new Gson().toJson(users);
	}

	public User convertJSONString(String jsonString) {
		User user = new Gson().fromJson(jsonString, User.class);
		return user;
	}

	public List<User> convertJSONString(String jsonString, boolean typeRequired) {
		List<User> users = null;
		Type type = new TypeToken<ArrayList<User>>() {
		}.getType();
		users = new Gson().fromJson(jsonString, type);
		return users;
	}

}
