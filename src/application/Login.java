package application;

import javafx.stage.Stage;

public class Login extends AbstractWindow {
	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("login.fxml");
			super.start(stage);
			stage.setTitle("Login");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
