package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;

/**
 * A singleton class represents customer order. Consist of method for managing
 * Menu's object in the Map<K,V> such as adding or removing the Menu's quantity.
 * 
 * @author Piyawat & Vichaphol
 *
 */
// write javadoc for extending Obsrvable is use
public class Order extends Observable {

	private Map<Menu, Integer> orders;
	// singleton instance for Order
	private static Order instance;

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
	 * Get all orders.
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
	 * Remove the menu from the order list.
	 * 
	 * @param Menu
	 *            wish to remove
	 * 
	 * @return true if the requested is successfully remove, false for not
	 */
	public boolean removeOrder(Menu order) {
		return removeFromMap(order);
	}

	/*
	 * Private method for removing an order requested by checking if the menu is in
	 * the list yet or not.
	 */
	private boolean removeFromMap(Menu order) {
		if (orders.containsKey(order)) {
			orders.put(order, orders.get(order) - 1);
			return true;
		}
		// the menu requested has not been ordered yet
		return false;
	}

	/**
	 * Remove all current orders.
	 */
	public void clearOrders() {
		orders.clear();
//		setChanged();
//		notifyObservers();
	}

	/**
	 * Method for getting all orders from parameter and return them as group of text
	 * in lines. for further use.
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

	// for lambda expression
	private int tmpTotal;

	// during in test
	public int getTotal() {
		// named temp for temporary
		// int temp = 0;
		// for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
		// Menu menu = order.getKey();
		// int qty = order.getValue();
		// int price = menu.getPrice();
		// temp += qty * price;
		// }
		// return temp;
		tmpTotal = 0;
		orders.forEach((k, v) -> tmpTotal += k.getPrice() * v);
		return tmpTotal;
	}

	// during in test
	public int getTotal(Map<Menu, Integer> map) {
		// named temp for temporary
		// int temp = 0;
		// for (Map.Entry<Menu, Integer> order : map.entrySet()) {
		// Menu menu = order.getKey();
		// int qty = order.getValue();
		// int price = menu.getPrice();
		// temp += qty * price;
		// }
		// return temp;
		tmpTotal = 0;
		map.forEach((k, v) -> tmpTotal += k.getPrice() * v);
		return tmpTotal;
	}

	// for testing
	public void printOrders() {
		orders.forEach((k, v) -> System.out.println("key: " + k.getName() + k.getPrice() + " value:" + v));
//		setChanged();
//		notifyObservers();
	}

	// for testing
	// public static void main(String[] args) {
	// Order om = Order.getInstance();
	// // test adding
	// om.addOrder(new Menu("cocoa", 20));
	// om.addOrder(new Menu("Steak", 100));
	// om.addOrder(new Menu("water", 10));
	// om.addOrder(new Menu("water", 10));
	// om.addOrder(new Menu("Steak", 100));
	// om.addOrder(new Menu("water", 10));
	// om.addOrder(new Menu("cocoa", 20));
	// om.addOrder(new Menu("Steak", 100));
	//
	// om.addOrder(new Menu("water", 10));
	// om.addOrder(new Menu("cocoa", 20));
	// om.addOrder(new Menu("cocoa", 20));
	// om.addOrder(new Menu("Steak", 100));
	// // test removing
	// om.removeOrder(new Menu("cocoa", 20));
	// om.removeOrder(new Menu("Steak", 100));
	// om.removeOrder(new Menu("water", 10));
	// om.printOrders();
	// System.out.println(om.getOrders().size());
	// }

}
