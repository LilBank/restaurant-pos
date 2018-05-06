package application;

import database.DBManager;
import javafx.stage.Stage;
import util.AbstractWindow;

/**
 * Main class that runs StartUp of the program with three options. Login,
 * SignUP, CustomerMode.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class Main extends AbstractWindow {

	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/startup.fxml");
			super.start(stage);
			stage.setTitle("Start Up");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBManager.connect();
		launch(args);
	}
}
