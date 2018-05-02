package application;

import controller.EMOrderController;
import javafx.stage.Stage;

/**
 * EMOrder(employee) class that extends AbstractWindow for capable of running
 * the method switchWindow. This window is for selecting the food, observing
 * customer's order, and check their bills.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class EMOrder extends AbstractWindow {
	//for setting the tablenumber to EMOrderController
	public EMOrder(String input) {
		EMOrderController.setTable(input);
	}

	public void start(Stage stage) {
		try {
			super.setFilename("view/normal-order.fxml");
			super.start(stage);
			stage.setTitle("Waiter Menu");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
