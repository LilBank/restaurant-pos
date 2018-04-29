package controller;

import application.CSTable;
import application.Main;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	// Alert dialog
	private Alert alert;

	/**
	 * Method for handling cancel button. When event receive Main scene is
	 * shown.
	 * 
	 * @param event
	 */
	public void cancelButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) cancel.getScene().getWindow(), new Main());
	}

	/**
	 * Method for handling login button. When event receive the implementation
	 * below is done.
	 * 
	 * @param event
	 */
	public void loginButtonHandler(ActionEvent event) {
		// empty username textfield
		if (username.getText().equals("")) {
			alert = new Alert(AlertType.ERROR, "Username is empty.", ButtonType.OK);
			alert.show();
		}
		// either password field is empty
		else if (password.getText().equals("")) {
			alert = new Alert(AlertType.ERROR, "Password or the confirmation is empty!", ButtonType.OK);
			alert.show();
		}
		// check if this user is valid
		else {
			int accessLevel = DBManager.login(username.getText(), password.getText());
			// int = 2 for manager mode
			if (accessLevel == 2) {
				alert = new Alert(AlertType.NONE, "ºË“π√–¥—∫ 2 ®È““““", ButtonType.OK);
				alert.show();
			}
			// int = 1 for normal mode
			if (accessLevel == 1) {
				alert = new Alert(AlertType.NONE, "ºË“π√–¥—∫ 1 ®È““““", ButtonType.OK);
				alert.show();
			}
			// wrong password
			if (accessLevel == 0) {
				alert = new Alert(AlertType.ERROR, "Wrong password!", ButtonType.OK);
				alert.show();
			}
			// User does not exists
			if (accessLevel == -1) {
				alert = new Alert(AlertType.ERROR, "User does not exists!", ButtonType.OK);
				alert.show();
			}
		}
	}
}
