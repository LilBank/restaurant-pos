package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderedMenu {

	private Map<Menu, Integer> orders;
	private static OrderedMenu instance;

	private OrderedMenu() {
		orders = new LinkedHashMap<>();
	}

	public static OrderedMenu getInstance() {
		if (instance == null)
			instance = new OrderedMenu();
		return instance;
	}

	public Map<Menu, Integer> getOrders() {
		return this.orders;
	}

	private void addToMap(Menu order) {
//		 if (!orders.containsKey(order)) {
//		System.out.println("add new");
//		 orders.put(order, 1);
//		 } else {
//		System.out.println("add exist");
//		 orders.put(order, orders.get(order.getName()) + 1);
//		 }
		
//		if (orders.get(order.getName()) >0) {
//			orders.put(order, 1);
//		} else {
//			orders.put(order, orders.get(order.getName()) + 1);
//		}
		
//		for (Map.Entry<Menu, Integer> x : orders.entrySet()) {
//			String name = x.getKey().getName();
//			Integer qty = x.getValue();
//			if (!name.equals(order.getName())) {
//				System.out.println("add new");
//				orders.put(order, 1);
//			} else {
//				System.out.println("add exist");
//				orders.put(order, qty + 1);
//			}
//		}
	}
	
	public void addOrder(Menu order) {
		addToMap(order);
	}

	// for testing
	public void printOrders() {
		orders.forEach((k, v) -> System.out.println("key: " + k.getName() + " value:" + v));
	}

	// for testing
	public static void main(String[] args) {
		OrderedMenu om = OrderedMenu.getInstance();
		om.addOrder(new Menu("water", 10));
		om.addOrder(new Menu("water", 10));
		om.addOrder(new Menu("water", 10));
		om.addOrder(new Menu("cocoa", 20));
		om.addOrder(new Menu("water", 10));
		om.addOrder(new Menu("cocoa", 20));
		om.printOrders();
		System.out.println(om.getOrders().size());
	}

}
