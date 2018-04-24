package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Main class that runs StartUp of the program with three options. Login,
 * SignUP, CustomerMode.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage stage) {
		try {
			String fxmlfile = "startup.fxml";
			URL url = getClass().getResource(fxmlfile);
			// Load the FXML and get reference to the loader
			FXMLLoader loader = new FXMLLoader(url);
			// Create scene graph from file (UI)
			Parent root = loader.load();
			// Show the scene
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.sizeToScene();
			stage.setTitle("Login");
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
