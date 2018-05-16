package application;

import javafx.stage.Stage;
import util.AbstractWindow;

/**
 * A User manager class that extends AbstractWindow for capable of running the
 * method switchWindow. Mainly this window is for manager who could remove the
 * user from the list and database.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class ManageUser extends AbstractWindow {
	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/manager-usermanager.fxml");
			super.start(stage);
			stage.setTitle("User Manager");
		} catch (Exception e) {
			System.out.println("Couldn't find the fxml file.");
		}
	}
}
