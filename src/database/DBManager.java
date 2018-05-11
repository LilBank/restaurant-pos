package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import model.Menu;
import model.PrivilegeEnum;
import model.User;
import util.PropertyManager;

/**
 * DBManager contains method for managing data on database. Also associate with
 * classes that use the database. With help from BCrypt all password are
 * encrypted.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class DBManager {
	// for single instantiation
	private static PropertyManager pm = PropertyManager.getInstance();
	// singleton instance of DBManager
	private static DBManager instance;
	private Connection connection;
	private String DB_URL = pm.getProperty("database.url");
	private String USER = pm.getProperty("database.user");
	private String PASS = pm.getProperty("database.password");
	/** Command for mysql database using JDBC */
	private String sqlCommand;

	/**
	 * Private constructor for DBManger. Getting the connection from the
	 * database.
	 */
	private DBManager() {
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method for getting the instance of DBManager with the condition if the
	 * instance is null, create new instance.
	 * 
	 * @return instance of the DBManager
	 */
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	/**
	 * Method for getting data from the database to check the Login's input.
	 * 
	 * @param username
	 *            from Login's input
	 * @param password
	 *            from Login's input
	 * @return 2 for manager, 1 for normal employee, 0 = wrong password, -1 =
	 *         user doesn't exists
	 */
	public int login(String user, String pass) {
		// sqlCommand = "SELECT * FROM User WHERE name = " + "'" + user + "'";
		sqlCommand = "SELECT * FROM User WHERE name = ?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			// if matches dbPass = hash password
			String dbPass = "";
			if (rs.next()) {
				dbPass = rs.getString("password");
			}
			if (BCrypt.checkpw(pass, dbPass)) {
				return rs.getInt("access type");
			}
			// wrong password
			if (!dbPass.equals("")) {
				return 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			// must close statement every time
			try {
				if (stmt != null) {
					stmt.close();
				}
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
	public void signUp(String user, String pass) {
		sqlCommand = "INSERT INTO `User` (`name`, `password`, `access type`) VALUES (?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			stmt.setString(1, user);
			String hashpw = BCrypt.hashpw(pass, BCrypt.gensalt());
			stmt.setString(2, hashpw);
			stmt.setInt(3, 1);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// must close statement every time
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method for getting data from the database to check whether if the
	 * username inputed has already exist or not.
	 * 
	 * @param username
	 *            from SignUp window
	 * @return false if username match, true if no match
	 */
	public boolean checkUser(String user) {
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
			// must close statement every time
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// username does not exist
		return true;
	}

	// during in test
	public List<String> getFoodUrl(String table) {
		List<String> temp = new ArrayList<>();
		sqlCommand = "SELECT * FROM " + table;
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String text = rs.getString("url");
				temp.add(text);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	/**
	 * Method for getting data from the database which are food names and prices
	 * to create a Menu object.
	 * 
	 * @param tablename
	 * @return List<Menu> of food names
	 */
	public List<Menu> getFoodname(String foodkind) {
		// named variable temp for temporary
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
			// must close statement every time
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	/**
	 * Method for removing image data from the database.
	 * 
	 * @param tablename
	 *            in the database
	 * @param food's
	 *            name
	 * @param food's
	 *            price
	 * @param url
	 */
	public void insertTo(String foodtable, String name, Integer price, String url) {
		// test connection
		sqlCommand = "INSERT INTO `" + foodtable + "` (`name`, `price`, `url`) VALUES (?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			stmt.setString(1, name);
			stmt.setInt(2, price);
			stmt.setString(3, url);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method for removing image data from the database
	 */
	public void removeImage(String foodtable, Menu item) {
		sqlCommand = "DELETE FROM " + foodtable + " WHERE name = ?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			stmt.setString(1, item.getName());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method for checking table existence in database.
	 * 
	 * @param table
	 *            number
	 * @return true if table exist, false if not
	 */
	public boolean checkTable(String tableNumber) {
		DatabaseMetaData dbm = null;
		try {
			dbm = connection.getMetaData();
			ResultSet table = dbm.getTables(null, null, tableNumber, null);
			// table exists
			if (table.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// table does not exist
		return false;
	}

	/**
	 * Method for creating a new table in database.
	 * 
	 * @param table
	 *            number
	 */
	public void createTable(String tableNumber) {
		tableNumber = "table" + tableNumber;
		sqlCommand = "CREATE TABLE " + tableNumber + "(name VARCHAR (255), price INT(11), quantity INT(11))";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// during in test
	public List<User> getDBUser() {
		// change to List<Menu>
		List<User> temp = new ArrayList<>();
		sqlCommand = "SELECT * FROM " + "User";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String text = rs.getString("name");
				User user = new User(text, PrivilegeEnum.USER);
				temp.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	/**
	 * Method for inserting current orders into the requested table in database.
	 * 
	 * @param tablenumber
	 * @param Map<Menu,Integer>
	 *            of orders
	 */
	public void orderToDB(String tableNumber, Map<Menu, Integer> map) {
		// name variable tmp for temporary
		String tabletmp = "table" + tableNumber;
		sqlCommand = "INSERT INTO `" + tabletmp + "` (`name`, `price`, `quantity`) VALUES (?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			for (Map.Entry<Menu, Integer> order : map.entrySet()) {
				stmt = connection.prepareStatement(sqlCommand);
				String name = order.getKey().getName();
				int price = order.getKey().getPrice();
				int qty = order.getValue();
				stmt.setString(1, name);
				stmt.setInt(2, price);
				stmt.setInt(3, qty);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method for getting all orders and from the wanted table in database and
	 * collect them as Map<Menu,Integer>.
	 * 
	 * @param tableNumber
	 * @return Map<Menu,Integer> of orders
	 */
	public Map<Menu, Integer> getDBOrders(String tableNumber) {
		// name variable temp for temporary
		Map<Menu, Integer> temp = new LinkedHashMap<>();
		String tabletmp = "table" + tableNumber;
		sqlCommand = "SELECT * FROM " + tabletmp;
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int qty = rs.getInt("quantity");
				Menu menu = new Menu(name, price);
				if (!temp.containsKey(menu)) {
					temp.put(menu, qty);
				} else {
					temp.put(menu, temp.get(menu) + qty);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	/**
	 * Method for clearing all records in the wanted database table.
	 * 
	 * @param tablenumber
	 */
	public void clearTable(String tableNumber) {
		PreparedStatement stmt = null;
		String tabletmp = "table" + tableNumber;
		sqlCommand = "DELETE FROM " + tabletmp;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			stmt.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method for inserting items, which is going to be paid, to the table in
	 * database with specific tbale number.
	 * 
	 * @param tablenumber
	 * @param list
	 *            of the table items
	 */
	public void insertToSum(Map<Menu, Integer> map) {
		sqlCommand = "INSERT INTO `Summary` (`FoodName`, `FoodPrice`, `Quantity`) VALUES (?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			for (Map.Entry<Menu, Integer> order : map.entrySet()) {
				stmt = connection.prepareStatement(sqlCommand);
				String name = order.getKey().getName();
				int price = order.getKey().getPrice();
				int qty = order.getValue();
				stmt.setString(1, name);
				stmt.setInt(2, price);
				stmt.setInt(3, qty);
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// during in test
	public boolean checkDBFood(String foodName, String tableNumber) {
		String tabletmp = "table" + tableNumber;
		sqlCommand = "SELECT * FROM " + tabletmp + " WHERE name = ?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlCommand);
			stmt.setString(1, foodName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
