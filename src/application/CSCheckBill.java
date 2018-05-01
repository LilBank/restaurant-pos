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
public class CSCheckBill extends AbstractWindow {
	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/customer-checkbill.fxml");
			super.start(stage);
			stage.setTitle("Check Bill");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
