package application;

import javafx.stage.Stage;
/**
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CSMenu extends AbstractWindow {
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
