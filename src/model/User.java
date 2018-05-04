package model;

import util.UserManager;

public class User {
	private String username;
	private PrivilageEnum privilage = PrivilageEnum.USER;

	public User(String username) {
		this.username = username;
	}

	public User(String username, PrivilageEnum num) {
		this.username = username;
		privilage = num;
	}

	public boolean isAdmin() {
		if (2 == UserManager.getInstance().getUser().privilage.getNumber()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getUsername(){
		return this.username;
	}
	public int getAccessLevel(){
		return privilage.getNumber();
	}
}
