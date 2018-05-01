package controller;

import application.Login;
import application.MGMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MNSelectionController {
	@FXML
	private Button image;
	@FXML
	private Button logout;
	@FXML
	private Button b2;

	/**
	 * Handler for image button. When event receive the manager menu scene is shown.
	 * 
	 */
	public void imageButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) image.getScene().getWindow(), new MGMenu());
	}
	/**
	 * Handler for logout button. When event receive the login scene is shown.
	 * 
	 */
	public void logoutButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) logout.getScene().getWindow(), new Login());
	}
}
