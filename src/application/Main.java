package application;

import javafx.stage.Stage;

/**
 * Main class that runs StartUp of the program with three options. Login,
 * SignUP, CustomerMode.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class Main extends AbstractWindow {

	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("startup.fxml");
			super.start(stage);
			stage.setTitle("Start Up");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
