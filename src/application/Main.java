package application;

import controller.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			/*
			 * Parent object for all nodes that have children in the scene graph
			 * (Anonymous Inner Type)
			 */
			Parent root = new Parent() {
			};
			Scene scene = new Scene(root);
			// ScreenController object for switching scenes
			ScreenController screenController = new ScreenController(scene);
			screenController.addScene("customer-table", FXMLLoader.load(getClass().getResource("customer-table.fxml")));
			screenController.addScene("customer-menu", FXMLLoader.load(getClass().getResource("customer-menu.fxml")));
			// Set the scene
			screenController.activate("customer-table");
			// Show the scene
			stage.setScene(scene);
			stage.sizeToScene();
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
