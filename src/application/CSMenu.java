package application;

import javafx.stage.Stage;

/**
 * CSMenu class that extends Application for capable of running the method
 * switchWindow. Mainly this window contains menu for customer which picture of
 * the menu is provided too.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class CSMenu extends AbstractWindow {

	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("customer-menu.fxml");
			super.start(stage);
			stage.setTitle("Menu");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
