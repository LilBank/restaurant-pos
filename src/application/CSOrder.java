package application;

import controller.CSOrderController;
import javafx.stage.Stage;
import util.AbstractWindow;

/**
 * CSOrder(customer) class that extends AbstractWindow for capable of running the method
 * switchWindow. This window is for selecting the food, which can be edited, in
 * the customer mode.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CSOrder extends AbstractWindow {

	// for setting the tablenumber to CSOrderController
	public CSOrder(String input) {
		CSOrderController.setTable(input);
	}

	public void start(Stage stage) {
		try {
			super.setFilename("view/customer-order.fxml");
			super.start(stage);
			stage.setTitle("Menu");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
