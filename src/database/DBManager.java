package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Menu;
import model.PrivilegeEnum;
import model.User;
import util.PropertyManager;

/**
 * DBManager contains method for managing data on database. Also associate with
 * classes that use the database.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class DBManager {
	private static PropertyManager pm = PropertyManager.getInstance();
	private static String DB_URL = pm.getProperty("database.url");
	private static String USER = pm.getProperty("database.user");
	private static String PASS = pm.getProperty("database.password");
	private static String sqlCommand;
	private static Connection connection;

	/**
	 * Constructor for initialize DBManager.
	 */
	public DBManager() {
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

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
		// sqlCommand = "SELECT * FROM User WHERE name = " + "'" + user + "'";
		sqlCommand = "SELECT * FROM User WHERE name = ?";
		PreparedStatement stmt = null;
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = connection.prepareStatement(sqlCommand);
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
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
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// not in any cases (ResultSet == null)
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
		sqlCommand = "INSERT INTO `User` (`name`, `password`, `access type`) VALUES (?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			stmt.setString(1, user);
			stmt.setString(2, pass);
			stmt.setInt(3, 1);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		// check if username does exist
		sqlCommand = "SELECT * FROM User WHERE name = ?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			int dbInt = 0;
			// if username found in database then changed the value of dbInt
			if (rs.next()) {
				dbInt = rs.getInt("access type");
			}
			if (dbInt == 1 || dbInt == 2) {
				// username exist
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// username does not exist
		return true;
	}

	// during in test
	public static List<String> getFoodname(String table, String column) {
		List<String> temp = new ArrayList<>();
		sqlCommand = "SELECT * FROM " + table;
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String text = rs.getString(column);
				temp.add(text);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	// during in test
	public static List<Menu> getFoodname(String foodkind) {
		List<Menu> temp = new ArrayList<>();
		sqlCommand = "SELECT * FROM " + foodkind;
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String text = rs.getString("name");
				int price = rs.getInt("price");
				Menu mn = new Menu(text, price);
				temp.add(mn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	// during in test
	// public static List<User> getUser() {
	// // change to List<Menu>
	// List<User> temp = new ArrayList<>();
	// sqlCommand = "SELECT * FROM " + "User";
	// try {
	// ResultSet rs = getData(sqlCommand);
	// while (rs.next()) {
	// String text = rs.getString("name");
	// User user = new User(text, PrivilegeEnum.USER);
	// temp.add(user);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return temp;
	// }

	/**
	 * Method for storing image from the database.
	 */
	public static void InsertTo(String foodtable, String name, String price, String url) {
		// sqlCommand = "INSERT INTO `" + foodtable + "` (`name`, `price`,
		// `url`)" +
		// "VALUES" + "(" + "'" + name + "'"
		// + ", '" + price + "'" + "'" + url + "'" + ")";
		//
		// try {
		// Connection connection = DriverManager.getConnection(DB_URL, USER,
		// PASS);
		// Statement statement = connection.createStatement();
		// statement.executeUpdate(sqlCommand);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

		// test connection
		String sql = "INSERT INTO " + foodtable + " VALUES (?,?,?)";
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, price);
			stmt.setString(3, url);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
