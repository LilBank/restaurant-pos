package application;

import javafx.stage.Stage;

/**
 * EMTableView(employee) class that extends AbstractWindow for capable of
 * running the method switchWindow. The window is for employee to wait for
 * customer response with permission to view their orders.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class EMTableView extends AbstractWindow {

	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/normal-tableview.fxml");
			super.start(stage);
			stage.setTitle("Tableview");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
