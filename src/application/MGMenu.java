package application;

import javafx.stage.Stage;

/**
 * MGMenu class that extends AbstractWindow for capable of running the method
 * switchWindow. Mainly this window is for manager who could edit the menu.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class MGMenu extends AbstractWindow {

	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/manager-menu.fxml");
			super.start(stage);
			stage.setTitle("Menu");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
