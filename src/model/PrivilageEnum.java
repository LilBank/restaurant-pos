package model;

public enum PrivilageEnum {
	USER(1), ADMIN(2);

	private int number;

	private PrivilageEnum(int i) {
		this.number = i;
	}

	public int getNumber() {
		return number;
	}

}
