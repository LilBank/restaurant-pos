package util;

import model.User;

/**
 * A singleton class to manage User when the program is running. Contains method
 * for setting and getting the User's data.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class UserManager {

	private User user;
	private static UserManager um;

	/**
	 * Return the instance of this class.
	 * 
	 * @return
	 */
	public static UserManager getInstance() {
		if (um == null) {
			um = new UserManager();
		}
		return um;
	}

	/**
	 * Set the current profile user.
	 * 
	 * @param User
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Get the current user profile.
	 * 
	 * @return current User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Check if the current user is administrator or not.
	 * 
	 * @return true if administrator
	 */
	public boolean isAdmin() {
		if (2 == user.getAccessLevel()) {
			return true;
		} else {
			return false;
		}
	}
}
