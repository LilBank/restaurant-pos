package controller;

import application.CSTable;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * LoginController contains method for handling all event receive from the
 * UserInterface.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class LoginController {
	@FXML
	private Button cancel;
	@FXML
	private Button login;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

	public void loginButtonHandler(ActionEvent event) {
		
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
