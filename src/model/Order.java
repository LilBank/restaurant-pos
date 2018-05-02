package model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A singleton class represents customer order. Consist of method for managing
 * Menu's object in the Map<K,V> such as adding or removing the Menu's quantity.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class Order {

	private Map<Menu, Integer> orders;
	private static Order instance;

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
		if (instance == null)
			instance = new Order();
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
	 * Private method for removing an order requested by checking if the menu is
	 * in the list yet or not.
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
	 * Remove all current ordering menus.
	 */
	public void clearOrders() {
		orders.clear();
	}

	// for testing
	public void printOrders() {
		orders.forEach((k, v) -> System.out.println("key: " + k.getName() + " value:" + v));
	}

	// for testing
	public static void main(String[] args) {
		Order om = Order.getInstance();
		// test adding
		om.addOrder(new Drink("cocoa", 20));
		om.addOrder(new Food("Steak", 100));
		om.addOrder(new Drink("water", 10));
		om.addOrder(new Drink("water", 10));
		om.addOrder(new Food("Steak", 100));
		om.addOrder(new Menu("water", 10));
		om.addOrder(new Drink("cocoa", 20));
		om.addOrder(new Food("Steak", 100));;
		om.addOrder(new Drink("water", 10));
		om.addOrder(new Drink("cocoa", 20));
		om.addOrder(new Drink("cocoa", 20));
		om.addOrder(new Food("Steak", 100));
		// test removing
		om.removeOrder(new Drink("cocoa", 20));
		om.removeOrder(new Food("Steak", 100));
		om.removeOrder(new Drink("water", 10));
		om.printOrders();
		System.out.println(om.getOrders().size());
	}

}
