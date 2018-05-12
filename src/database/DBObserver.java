package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Observer class for Database. Contains method for getting and comparing data
 * changes by table rows.
 * 
 * @author Piyawat Setthitikun & Vichphol
 *
 */
public class DBObserver extends Observable {
	private static DBObserver instance = new DBObserver();
	private DBManager dbm = DBManager.getInstance();
	private List<Integer> rows = new ArrayList<>();
	private List<Boolean> changes = new ArrayList<>();

	/**
	 * Get in stance of this class.
	 * 
	 * @return DBObserver instance
	 */
	public static DBObserver getInstance() {
		return instance;
	}

	/**
	 * Method for getting all rows from all tables and put it in the list for
	 * single time.
	 */
	public void setAllRows() {
		for (int i = 1; i <= 8; i++) {
			rows.add(dbm.refreshRecords(i + ""));
		}
	}

	/**
	 * Method for setting all the table changes to false for single time.
	 */
	public void setChanges() {
		for (int i = 1; i <= 8; i++) {
			changes.add(false);
		}
	}

	/**
	 * Compare row differences between the old row and new row. If there is a
	 * difference then set that table index to true.
	 */
	public void findChanges() {
		for (int i = 1; i <= 8; i++) {
			int temp = dbm.refreshRecords(i + "");
			if (temp > rows.get(i - 1)) {
				changes.set(i - 1, true);
				rows.set(i - 1, temp);
			}
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Method for setting all the table changes to false .
	 */
	public void setChangesBack() {
		for (int i = 0; i < 8; i++) {
			changes.set(i, false);
		}
	}

	/**
	 * Method for return list of table that changes.
	 * 
	 * @return list of table changes
	 */
	public List<Integer> getChanges() {
		List<Integer> tmp = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			if (changes.get(i - 1)) {
				tmp.add(i);
			}
		}
		return tmp;
	}

}
