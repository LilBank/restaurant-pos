package application;

import controller.CSMenuController;
import javafx.stage.Stage;

/**
 * CSMenu class that extends AbstractWindow for capable of running the method
 * switchWindow. This window is for selecting the food, which can be edited, in
 * the customer mode.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CSMenu extends AbstractWindow {

	// for setting the tablenumber to CSMenuController
	public CSMenu(String input) {
		CSMenuController.setTable(input);
	}

	public void start(Stage stage) {
		try {
			super.setFilename("view/customer-menu.fxml");
			super.start(stage);
			stage.setTitle("Customer Menu");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
