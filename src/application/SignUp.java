package application;

import javafx.stage.Stage;

public class SignUp extends AbstractWindow {

	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("signup.fxml");
			super.start(stage);
			stage.setTitle("Sign Up");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
