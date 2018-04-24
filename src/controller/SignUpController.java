package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * SignUpController contains method for handling all event receive from the
 * UserInterface.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class SignUpController {
	@FXML
	private Button confirm;
	@FXML
	private Button cancel;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField password2;

	public void confirmButtonHandler(ActionEvent event) {

	}

	/**
	 * Method for handling cancel button. When event receive Main scene is
	 * shown.
	 * 
	 * @param event
	 */
	public void cancelButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) cancel.getScene().getWindow(), new Main());
	}
}
