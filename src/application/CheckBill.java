package application;

import controller.CheckBillController;
import javafx.stage.Stage;
import util.AbstractWindow;

/**
 * CheckBill class that extends AbstractWindow for capable of running the method
 * switchWindow. This is the last process of the application which can calculate
 * the money paid by the customer and also print the receipt.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CheckBill extends AbstractWindow {

	// for setting the bill in CheckBillController
	public CheckBill(String total) {
		CheckBillController.setBill(total);
	}

	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/checkbill.fxml");
			super.start(stage);
			stage.setTitle("Check Bill");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
