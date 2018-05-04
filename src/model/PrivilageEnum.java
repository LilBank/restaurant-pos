package model;

public enum PrivilageEnum {
	
	USER(1), ADMIN(2);

	// attributes of the enum members
	private int number;

	// private constructor for enum
	private PrivilageEnum(int i) {
		this.number = i;
	}

	/**
	 * Get the value of the enum CONSTANT.
	 * 
	 * @return int which value of the privilage
	 */
	public int getNumber() {
		return number;
	}

}
