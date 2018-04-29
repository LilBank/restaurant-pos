package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import util.PropertyManager;

/**
 * DBManager contains method for managing data on database. Also associate with
 * classes that use database.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class DBManager {
	private static PropertyManager pm = PropertyManager.getInstance();
	private static String DB_URL = pm.getProperty("database.url");
	private static String USER = pm.getProperty("database.user");
	private static String PASS = pm.getProperty("database.password");

	public static int login(String user, String pass) {
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

}
