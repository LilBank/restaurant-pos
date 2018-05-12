package util;

import java.util.ArrayList;
import java.util.List;

import database.DBManager;

public class DBObserver {
	private static DBObserver instance = new DBObserver();
	private DBManager dbm = DBManager.getInstance();
	private List<Integer> rows = new ArrayList<>();
	private List<Boolean> changes = new ArrayList<>();

	public static DBObserver getInstance() {
		return instance;
	}

	public void setAllRows() {
		for (int i = 1; i <= 8; i++) {
			rows.add(dbm.refreshRecords(i + ""));
		}
		rows.forEach((x) -> System.out.println(x));
	}

	public void setChanges() {
		for (int i = 1; i <= 8; i++) {
			changes.add(false);
		}
		changes.forEach((x) -> System.out.println(x));
	}

	public void findChanges() {
		for (int i = 1; i <= 8; i++) {
			int temp = dbm.refreshRecords(i + "");
			if (temp > rows.get(i - 1)) {
				changes.set(i - 1, true);
				rows.set(i - 1, temp);
			}
		}
		changes.forEach((x) -> System.out.println(x));
	}

	public List<Boolean> getChanges() {
		return changes;
	}

	public void setChangesBack() {
		for (int i = 0; i < 8; i++) {
			changes.set(i, false);
		}
		changes.forEach((x) -> System.out.println(x));
	}

}
