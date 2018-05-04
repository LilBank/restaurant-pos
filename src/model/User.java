package model;

/**
 * An object that represents a User. Consist of username and PrivilageEnum.
 * Contains method for getting private attributes and other uses.
 * 
 * @author Piyawat & Vichphol & Taweerat
 *
 */
public class User {
	private String username;
	private PrivilegeEnum privilage = PrivilegeEnum.USER;

	/**
	 * Constructor for employee. Privilege is set to 1.
	 * 
	 * @param username
	 */
	public User(String username) {
		this.username = username;
	}

	/**
	 * Constructor for manager. Privilege is set to 2.
	 * 
	 * @param username
	 */
	public User(String username, PrivilegeEnum num) {
		this.username = username;
		privilage = num;
	}

	/**
	 * Check if the current user is administrator or not.
	 * 
	 * @return true if administrator
	 */
	public boolean isAdmin() {
		if (2 == privilage.getNumber()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Return the current user username.
	 * 
	 * @return username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Return the current access level in Integer.
	 * 
	 * @return int for access level
	 */
	public int getAccessLevel() {
		return privilage.getNumber();
	}
}
