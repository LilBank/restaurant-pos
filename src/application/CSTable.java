package application;

import javafx.stage.Stage;
import util.AbstractWindow;

/**
 * CSTable(customer) class that extends AbstractWindow for capable of running the method
 * switchWindow. This window is for entering the customers table number for food
 * ordering.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CSTable extends AbstractWindow {

	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/customer-table.fxml");
			super.start(stage);
			stage.setTitle("Table Number");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
