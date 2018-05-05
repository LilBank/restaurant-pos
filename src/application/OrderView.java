package application;

import java.util.List;

import controller.OrderViewController;
import javafx.stage.Stage;
import util.AbstractWindow;

/**
 * OrderView(manager/employee) class that extends AbstractWindow for capable of
 * running the method switchWindow. This window is for selecting the food,
 * observing customer's order, and check their bills.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class OrderView extends AbstractWindow {

	// for setting the tablenumber to EMOrderController
	public OrderView(String input, List<String> foodnames, List<String> drinknames) {
		OrderViewController.setTable(input);
		OrderViewController.setMenu(foodnames, drinknames);
	}

	public void start(Stage stage) {
		try {
			super.setFilename("view/orderview.fxml");
			super.start(stage);
			stage.setTitle("Menu");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
