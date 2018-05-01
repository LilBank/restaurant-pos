package application;

import javafx.stage.Stage;

/**
 * CheckBill class that extends AbstractWindow for capable of running the method
 * switchWindow. This is the last process of the application which can
 * calculates the money paid by customer and also print the receipt.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CheckBill extends AbstractWindow {
	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/customer-checkbill");
			stage.setTitle("Check Bill");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
