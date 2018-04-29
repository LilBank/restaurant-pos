package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.PropertyManager;

/**
 * DBManager contains method for managing data on database. Also associate with
 * classes that use the database.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class DBManager {
	private static PropertyManager pm = PropertyManager.getInstance();
	private static String DB_URL = pm.getProperty("database.url");
	private static String USER = pm.getProperty("database.user");
	private static String PASS = pm.getProperty("database.password");
	private static String sqlCommand;

	/**
	 * Method for retrieving data from the database to check the Login's input.
	 * 
	 * @param username
	 *            from Login's input
	 * @param password
	 *            from Login's input
	 * @return 2 for manager, 1 for normal employee, 0 = wrong password, -1 =
	 *         user doesn't exists
	 */
	public static int login(String user, String pass) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			sqlCommand = "SELECT * FROM User WHERE name = " + "'" + user + "'";
			ResultSet rs = statement.executeQuery(sqlCommand);
			String dbPass = "";
			if (rs.next()) {
				dbPass = rs.getString("password");
			}
			if (pass.equals(dbPass)) {
				return rs.getInt("access type");
			}
			// wrong password
			if (!dbPass.equals("")) {
				return 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		// not in any cases
		return -1;
	}

	/**
	 * Method for inserting data(new user's data) to the database. The access
	 * type is set to 1 by default but can be change later on.
	 * 
	 * @param username
	 *            from SignUp window
	 * @param password
	 *            from SignUp window
	 */
	public static void signUp(String user, String pass) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			sqlCommand = "INSERT INTO `User` (`name`, `password`, `access type`)" + "VALUES" + "(" + "'" + user + "'"
					+ ", '" + pass + "', '1')";
			statement.executeUpdate(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for retrieving data from the database to check whether if the
	 * username inputed has already exist or not.
	 * 
	 * @param username
	 *            from SignUp window
	 * @return false if username match, true if no match
	 */
	public static boolean checkUser(String user) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			// check if username does exists
			sqlCommand = "SELECT * FROM User WHERE name = " + "'" + user + "'";
			ResultSet rs = statement.executeQuery(sqlCommand);
			int dbInt = 0;
			// if username found in database then changed the value of dbInt
			if (rs.next()) {
				dbInt = rs.getInt("access type");
			}
			if (dbInt == 1 || dbInt == 2) {
				// username exists
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// username does not exist
		return true;
	}

}
