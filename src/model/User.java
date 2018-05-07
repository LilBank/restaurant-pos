package model;

/**
 * An object that represents a User. Consist of username and PrivilageEnum.
 * Contains method for getting private attributes and other uses.
 * 
 * @author Piyawat & Vichphol & TA'Taweerat
 *
 */
public class User {
	private String username;
	private PrivilegeEnum privilage;

	/**
	 * Constructor for user.
	 * 
	 * @param username
	 */
	public User(String username, PrivilegeEnum num) {
		this.username = username;
		privilage = num;
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
