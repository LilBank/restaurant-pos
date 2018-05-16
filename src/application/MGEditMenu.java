package application;

import javafx.stage.Stage;
import util.AbstractWindow;

/**
 * MGEditMenu(manager) class that extends AbstractWindow for capable of running the method
 * switchWindow. Mainly this window is for manager who could edit the menu.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class MGEditMenu extends AbstractWindow {

	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/manager-menu.fxml");
			super.start(stage);
			stage.setTitle("Manager Menu");
		} catch (Exception e) {
			System.out.println("Couldn't find the fxml file.");
		}
	}
}
