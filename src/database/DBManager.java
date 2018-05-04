package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	/**
	 * Method for retrieving data from the database to check the Login's input.
	 * 
	 * @param username
	 *            from Login's input
	 * @param password
	 *            from Login's input
	 * @return 2 for manager, 1 for normal employee, 0 = wrong password, -1 = user
	 *         doesn't exists
	 */
	public static int login(String user, String pass) {
		sqlCommand = "SELECT * FROM User WHERE name = " + "'" + user + "'";
		try {
			ResultSet rs = getData(sqlCommand);
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
		// not in any cases (ResultSet == null)
		return -1;
	}

	/**
	 * Method for inserting data(new user's data) to the database. The access type
	 * is set to 1 by default but can be change later on.
	 * 
	 * @param username
	 *            from SignUp window
	 * @param password
	 *            from SignUp window
	 */
	public static void signUp(String user, String pass) {
		sqlCommand = "INSERT INTO `User` (`name`, `password`, `access type`)" + "VALUES" + "(" + "'" + user + "'"
				+ ", '" + pass + "', '1')";
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for retrieving data from the database to check whether if the username
	 * inputed has already exist or not.
	 * 
	 * @param username
	 *            from SignUp window
	 * @return false if username match, true if no match
	 */
	public static boolean checkUser(String user) {
		// check if username does exist
		sqlCommand = "SELECT * FROM User WHERE name = " + "'" + user + "'";
		try {
			ResultSet rs = getData(sqlCommand);
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
		}
		// username does not exist
		return true;
	}

	// during in test
	public static List<String> getFoodname(String foodkind) {
		List<String> temp = new ArrayList<>();
		sqlCommand = "SELECT * FROM " + foodkind;
		try {
			ResultSet rs = getData(sqlCommand);
			while (rs.next()) {
				String text = rs.getString("name");
				temp.add(text);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	// during in test for shortening codes
	public static ResultSet getData(String sqlCommand) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			return statement.executeQuery(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// never reaches because DB_URL is always found
		return null;
	}

	/**
	 * Method for storing image from the database.
	 */
	public static void ImageToDB(String name, String url) {
		sqlCommand = "INSERT INTO `Images` (`url`, `name`)" + "VALUES" + "(" + "'" + url + "'" + ", '" + name + "')";

		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlCommand);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method for getting list of URl.
	 *  
	 * @param name
	 * @return list containing url
	 */
	public static List<String> getImageURL(String name) {
		sqlCommand = "SELECT * FROM " + name;
		List<String> temp = new ArrayList<>();
		try {
			ResultSet rs = getData(sqlCommand);
			while (rs.next()) {
				String url = rs.getString("url");
				temp.add(url);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

}
