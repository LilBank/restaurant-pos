package application;

import javafx.stage.Stage;
import util.AbstractWindow;

/**
 * MGTableView(manager) class that extends AbstractWindow for capable of running
 * the method switchWindow. The window is for employee to wait for customer
 * response with full permission to remove customer orders.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class MGTableView extends AbstractWindow {
	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/manager-tableview.fxml");
			super.start(stage);
			stage.setTitle("Manager Tableview");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
