package controller;

import application.Login;
import application.Main;
import database.DBManager;
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
	 * implementation below is done. Every fail cases gives different Alert
	 * reply message.
	 * 
	 * @param event
	 */
	public void confirmButtonHandler(ActionEvent event) {
		// empty username textfield
		if (username.getText().equals("")) {
			alert = new Alert(AlertType.ERROR, "Username is empty.", ButtonType.OK);
			alert.show();
		}
		// either password or password2 field is empty
		else if (password.getText().equals("") || password2.getText().equals("")) {
			alert = new Alert(AlertType.ERROR, "Password or the confirmation is empty!", ButtonType.OK);
			alert.show();
		}
		// password mismatch
		else if (!password.getText().equals(password2.getText())) {
			alert = new Alert(AlertType.ERROR, "Password and the confirmation does not match.", ButtonType.OK);
			alert.show();
		}
		// password match
		else {
			// check username existence
			boolean allow = DBManager.checkUser(username.getText());
			if (!allow) {
				alert = new Alert(AlertType.ERROR, "Username already exist! Please use another username.",
						ButtonType.OK);
				alert.show();
			}
			if (allow) {
				alert = new Alert(AlertType.NONE,
						"You are registered as an restaurant employee. Press ok to continue...", ButtonType.OK);
				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						DBManager.signUp(username.getText(), password.getText());
						ScreenController.switchWindow((Stage) confirm.getScene().getWindow(), new Login());
					}
				});
			}
		}

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
