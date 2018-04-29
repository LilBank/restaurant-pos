package controller;

import javax.swing.JFrame;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	// Alert dialog
	private Alert alert;

	/**
	 * Method for handling confirm button. When event receive then the
	 * implementation below is worked.
	 * 
	 * @param event
	 */
	public void confirmButtonHandler(ActionEvent event) {
		if (username.getText().equals("")) {
			alert = new Alert(AlertType.ERROR, "Username is empty.", ButtonType.OK);
		}
		if (!password.getText().equals(password2.getText())) {
			alert = new Alert(AlertType.ERROR, "Password and the confirmation does not match.", ButtonType.OK);
		}
		if (password.getText().equals(password2.getText())) {
			alert = new Alert(AlertType.NONE, "ºË“π®È““““", ButtonType.OK);
		}
		alert.show();
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
