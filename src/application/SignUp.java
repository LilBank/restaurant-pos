package application;

import javafx.stage.Stage;

/**
 * SignUp class that extends AbstractWindow for capable of running the method
 * switchWindow. This window is for new user to registar his/her account to the
 * database.
 * 
 * @author Piyawat & Vichapol
 *
 */
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
