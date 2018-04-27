package controller;

import application.CSTable;
import application.Main;
import database.DBManager;
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

	/**
	 * Method for handling cancel button. When event receive Main scene is
	 * shown.
	 * 
	 * @param event
	 */
	public void cancelButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) cancel.getScene().getWindow(), new Main());
	}

	public void loginButtonHandler(ActionEvent event) {
		int tmp = DBManager.login(username.getText(), password.getText());
		if (tmp == 1)
			ScreenController.switchWindow((Stage) cancel.getScene().getWindow(), new Main());
	}
}
