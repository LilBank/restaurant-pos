package model;

import java.util.Observable;

import controller.OrderViewController;

/**
 * Under construction.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class OrderTable implements java.util.Observer {

	// instance of classes
	private static OrderViewController ovc;
	private static Order o;

	// during in test
	// constructor is clarify in OrderView to add it into observable list
	public OrderTable() {
		o = Order.getInstance();
		ovc = OrderViewController.getInstance();
	}

	// during in test
	public void display() {
		String text = null;
		try {
			text = o.orderToText(o.getOrders());
			System.out.println("text is not null");
		} catch (NullPointerException ex) {
			System.out.println("text is null");
			ex.printStackTrace();
		}
		try {
			ovc.getDisplay();
			System.out.println("display is not null");
		} catch (NullPointerException ex) {
			System.out.println("display is null");
			ex.printStackTrace();
		}
		ovc.setDisplay(text);
		System.out.println("display in OrderTable is working");
		// missing some action
		// must do the same as normal menu adding button
	}

	// during in test
	@Override
	public void update(Observable o, Object arg) {
		display();
		System.out.println("method update is working");
	}
}
