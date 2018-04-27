package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	static final String DB_URL = "jdbc:mysql://35.194.158.90:3306/BankDB?useSSL=false";
	static final String USER = "bank";
	static final String PASS = "helloworld";
	// static final Connection connection = null;

	// static {
	// try {
	// Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

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

	public static void createUser(String user, String pass) {

	}

}
