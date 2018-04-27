package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.PropertyManager;

/**
 * DBManager contains method for managing data on database. Also associate with
 * classes that use database.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class DBManager {

	public static int login(String user, String pass) {
		PropertyManager pm = PropertyManager.getInstance();
		String DB_URL = pm.getProperty("database.url");
		String USER = pm.getProperty("database.user");
		String PASS = pm.getProperty("database.password");
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM User WHERE name = " + "'" + user + "'");
			String dbPass = "";
			if (rs.next()) {
				dbPass = rs.getString("password");
			}
			if (pass.equals(dbPass))
				return rs.getInt("access type");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public static void createUser(String user, String pass) {

	}

}
