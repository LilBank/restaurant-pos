package application;

import database.DBManager;
import javafx.stage.Stage;
import util.AbstractWindow;
import util.ImageFactory;

/**
 * Main class that runs StartUp of the program with three options. Login,
 * SignUP, CustomerMode. Also get the data from database.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class Main extends AbstractWindow {

	@Override
	public void start(Stage stage) {
		try {
			ImageFactory instance = ImageFactory.getInstance();
			instance.loadDrinkImage();
			instance.loadFoodImage();
			super.setFilename("view/startup.fxml");
			super.start(stage);
			stage.setTitle("Start Up");
		} catch (Exception e) {
			System.out.println("Couldn't find the fxml file.");
		}
	}

	public static void main(String[] args) {
		DBManager.getInstance();
		launch(args);
	}
}
