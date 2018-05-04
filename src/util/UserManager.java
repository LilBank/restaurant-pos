package util;

import model.User;

/**
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class UserManager {
	
	private User user;
	private static UserManager um;

	public static UserManager getInstance() {
		if (um == null) {
			um = new UserManager();
		}
		return um;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
