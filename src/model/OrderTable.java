package model;

import java.util.Observable;

import controller.OrderViewController;

public class OrderTable implements java.util.Observer {

	private static OrderViewController ovc;
	private static Order o;

	public OrderTable() {
		o = Order.getInstance();
		ovc = OrderViewController.getInstance();
	}

	public void display() {
		String text = null;
		try {
			text = o.orderToText();
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
		ovc.display(text);
		System.out.println("display in OrderTable is working");
	}

	@Override
	public void update(Observable o, Object arg) {
		display();
		System.out.println("method update is working");
	}
}
