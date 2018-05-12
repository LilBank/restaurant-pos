package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;

import database.DBManager;

/**
 * A singleton class represents customer order. Consist of method for managing
 * Menu's object in the Map<K,V> such as adding or removing the Menu's quantity.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class Order extends Observable {

	// current orders
	private Map<Menu, Integer> orders;
	// orders from database
	private Map<Menu, Integer> ordersTmp;
	// singleton instance for Order
	private static Order instance;
	// for lambda expression
	private int tmpTotal;
	// instance of DBManager
	private static DBManager dbm = DBManager.getInstance();

	// private constructor
	private Order() {
		// Lazy instantiation
		orders = new LinkedHashMap<>();
	}

	/**
	 * Get a singleton instance of this class
	 * 
	 * @return the OrderedMenu object
	 */
	public static Order getInstance() {
		if (instance == null) {
			instance = new Order();
		}
		return instance;
	}

	/**
	 * Get list of current orders.
	 * 
	 * @return orders in Map<K,V>
	 */
	public Map<Menu, Integer> getOrders() {
		return this.orders;
	}

	/*
	 * Private method for sorting whether the menu is ordered yet or not.
	 */
	private void addToMap(Menu order) {
		if (!orders.containsKey(order)) {
			orders.put(order, 1);
		} else {
			orders.put(order, orders.get(order) + 1);
		}
	}

	/**
	 * Add the menu to the order list.
	 * 
	 * @param Menu
	 *            wish to order
	 */
	public void addOrder(Menu order) {
		addToMap(order);
		setChanged();
		notifyObservers();
	}

	/**
	 * Remove all current orders.
	 */
	public void clearOrders() {
		orders.clear();
		setChanged();
		notifyObservers();
	}

	// during in test
	public void clearOrdersTmp() {
		ordersTmp.clear();
	}

	/**
	 * Method for getting all orders from parameter and return them as group of
	 * text in lines for further use.
	 * 
	 * @return orders in texts
	 */
	public String orderToText(Map<Menu, Integer> map) {
		String text = "";
		for (Map.Entry<Menu, Integer> order : map.entrySet()) {
			Menu menu = order.getKey();
			int qty = order.getValue();
			String name = menu.getName();
			int price = menu.getPrice() * qty;
			// separate each order by \n
			text += String.format("%-35s %10d %10d\n", name, qty, price);
		}
		return text;
	}

	/**
	 * Method for returning the current total.
	 * 
	 * @return
	 */
	public int getTotal() {
		tmpTotal = 0;
		orders.forEach((k, v) -> tmpTotal += k.getPrice() * v);
		return tmpTotal;
	}

	/**
	 * Method for getting the total of the ordered orders.
	 * 
	 * @param Map<K,V>
	 *            wanted to get total
	 * @return total
	 */
	public int getTotal(Map<Menu, Integer> map) {
		tmpTotal = 0;
		map.forEach((k, v) -> tmpTotal += k.getPrice() * v);
		return tmpTotal;
	}

	/**
	 * Getting the orders from the wanted table in database.
	 * 
	 * @param tableNumber
	 * @return orders from database
	 */
	public Map<Menu, Integer> getDBOrders(String tableNumber) {
		refreshDBOrders(tableNumber);
		return ordersTmp;
	}

	/*
	 * Private method for getting orders from the wanted table in the database.
	 */
	private void refreshDBOrders(String tableNumber) {
		ordersTmp = dbm.getDBOrders("table" + tableNumber);
	}

	/**
	 * Method for getting the Menu object with String receive.
	 * 
	 * @param foodName
	 *            as String
	 * @return Menu object
	 */
	public Menu getMenu(String foodName) {
		Menu menu = null;
		for (Map.Entry<Menu, Integer> order : ordersTmp.entrySet()) {
			if (order.getKey().getName().equals(foodName)) {
				menu = order.getKey();
			}
		}
		return menu;
	}
}
