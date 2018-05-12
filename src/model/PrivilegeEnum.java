package model;

/**
 * Set of privileges constant.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public enum PrivilegeEnum {

	USER(1), ADMIN(2);

	private int number;

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
