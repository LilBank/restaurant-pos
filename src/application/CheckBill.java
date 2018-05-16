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

	public CheckBill(String total, String tablenumber) {
		CheckBillController.setBill(total);
		CheckBillController.setTable(tablenumber);
	}

	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/checkbill.fxml");
			super.start(stage);
			stage.setTitle("Check Bill");
		} catch (Exception e) {
			System.out.println("Couldn't find the fxml file.");
		}
	}
}
