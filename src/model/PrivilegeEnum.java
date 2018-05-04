package model;

/**
 * Set of privileges constant.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public enum PrivilegeEnum {

	USER(1), ADMIN(2);

	// attributes of the constant members
	private int number;

	// private constructor for constant
	private PrivilegeEnum(int i) {
		this.number = i;
	}

	/**
	 * Get the value of the constant.
	 * 
	 * @return int which value of the privilege
	 */
	public int getNumber() {
		return number;
	}

}
