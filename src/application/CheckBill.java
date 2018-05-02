package application;

import javafx.stage.Stage;

/**
 * CheckBill class that extends AbstractWindow for capable of running the method
 * switchWindow. This is the last process of the application which can calculate
 * the money paid by the customer and also print the receipt.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CheckBill extends AbstractWindow {

	private String total;

	// for getting the total from CSMenu
	public CheckBill(String total) {
		this.total = total;
	}

	public int getTotal() {
		return Integer.parseInt(this.total);
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
