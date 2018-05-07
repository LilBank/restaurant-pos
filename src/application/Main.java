package application;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import database.DBManager;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Order;
import util.AbstractWindow;
import util.DownloadTask;
import util.ImageFactory;

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
		// opening connection to database with private constructor
		DBManager.getInstance();
		ImageFactory instance = ImageFactory.getInstance();
		instance.setFoodImage();
		instance.setDrinkImage();
		launch(args);
	}
}
