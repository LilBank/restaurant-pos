package application;

import java.util.concurrent.Executors;

import com.sun.corba.se.impl.orbutil.closure.Future;

import database.DBManager;
import javafx.stage.Stage;
import util.AbstractWindow;
import util.DownloadTask;
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
			long start = System.nanoTime();
			System.out.println("start: " + (System.nanoTime() - start / 10e9));
			instance.setFoodImage();
			instance.setDrinkImage();
			// Future<List<Button>> future = ExecutorService.submit(new DownloadTask());
			super.setFilename("view/startup.fxml");
			super.start(stage);
			stage.setTitle("Start Up");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// opening connection to database with private constructor
		DBManager.getInstance();
		launch(args);
	}
}
