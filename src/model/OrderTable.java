package model;

import java.util.Observable;

import controller.OrderViewController;

public class OrderTable implements java.util.Observer {

	private static OrderViewController ovc = OrderViewController.getInstance();
	private static Order o = Order.getInstance();

	public void display() {
		try {
			String text = o.orderToText();
			ovc.display(text);
			throw new NullPointerException();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			display();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			throw new NullPointerException();
		}
	}
}
